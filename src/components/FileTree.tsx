import { ProjectFile } from "../data/projectFiles";
import {
  FolderOpen,
  FileCode2,
  ChevronRight,
  ChevronDown,
} from "lucide-react";
import { useState } from "react";

interface FileTreeProps {
  files: ProjectFile[];
  selectedFileId: string;
  onSelectFile: (id: string) => void;
}

interface TreeNode {
  name: string;
  path: string;
  children: Map<string, TreeNode>;
  file?: ProjectFile;
}

function buildTree(files: ProjectFile[]): TreeNode {
  const root: TreeNode = { name: "", path: "", children: new Map() };

  for (const file of files) {
    const parts = file.path.split("/");
    let current = root;

    for (let i = 0; i < parts.length; i++) {
      const part = parts[i];
      if (!current.children.has(part)) {
        current.children.set(part, {
          name: part,
          path: parts.slice(0, i + 1).join("/"),
          children: new Map(),
        });
      }
      current = current.children.get(part)!;
    }
    current.file = file;
  }

  return root;
}

function TreeNodeComponent({
  node,
  depth,
  selectedFileId,
  onSelectFile,
  defaultOpen,
}: {
  node: TreeNode;
  depth: number;
  selectedFileId: string;
  onSelectFile: (id: string) => void;
  defaultOpen: boolean;
}) {
  const [isOpen, setIsOpen] = useState(defaultOpen);
  const isFolder = node.children.size > 0 && !node.file;
  const isFile = !!node.file;
  const isSelected = isFile && node.file?.id === selectedFileId;

  const sortedChildren = Array.from(node.children.values()).sort((a, b) => {
    const aIsFolder = a.children.size > 0 && !a.file;
    const bIsFolder = b.children.size > 0 && !b.file;
    if (aIsFolder && !bIsFolder) return -1;
    if (!aIsFolder && bIsFolder) return 1;
    return a.name.localeCompare(b.name);
  });

  if (isFolder) {
    return (
      <div>
        <button
          onClick={() => setIsOpen(!isOpen)}
          className="flex w-full items-center gap-1.5 rounded-md px-2 py-1 text-left text-sm text-slate-300 transition-colors hover:bg-slate-700/50 hover:text-white"
          style={{ paddingLeft: `${depth * 16 + 8}px` }}
        >
          {isOpen ? (
            <ChevronDown className="h-3.5 w-3.5 shrink-0 text-slate-500" />
          ) : (
            <ChevronRight className="h-3.5 w-3.5 shrink-0 text-slate-500" />
          )}
          <FolderOpen className="h-4 w-4 shrink-0 text-amber-400" />
          <span className="truncate font-medium">{node.name}</span>
        </button>
        {isOpen && (
          <div>
            {sortedChildren.map((child) => (
              <TreeNodeComponent
                key={child.path}
                node={child}
                depth={depth + 1}
                selectedFileId={selectedFileId}
                onSelectFile={onSelectFile}
                defaultOpen={depth < 2}
              />
            ))}
          </div>
        )}
      </div>
    );
  }

  if (isFile) {
    const langColor =
      node.file!.language === "kotlin"
        ? "text-purple-400"
        : node.file!.language === "xml"
        ? "text-orange-400"
        : "text-emerald-400";

    return (
      <button
        onClick={() => onSelectFile(node.file!.id)}
        className={`flex w-full items-center gap-1.5 rounded-md px-2 py-1 text-left text-sm transition-all ${
          isSelected
            ? "bg-emerald-500/20 text-emerald-300 ring-1 ring-emerald-500/30"
            : "text-slate-400 hover:bg-slate-700/50 hover:text-slate-200"
        }`}
        style={{ paddingLeft: `${depth * 16 + 8}px` }}
      >
        <FileCode2 className={`h-4 w-4 shrink-0 ${langColor}`} />
        <span className="truncate">{node.name}</span>
      </button>
    );
  }

  return null;
}

export default function FileTree({
  files,
  selectedFileId,
  onSelectFile,
}: FileTreeProps) {
  const tree = buildTree(files);
  const sortedChildren = Array.from(tree.children.values()).sort((a, b) => {
    const aIsFolder = a.children.size > 0 && !a.file;
    const bIsFolder = b.children.size > 0 && !b.file;
    if (aIsFolder && !bIsFolder) return -1;
    if (!aIsFolder && bIsFolder) return 1;
    return a.name.localeCompare(b.name);
  });

  return (
    <div className="space-y-0.5 py-2">
      {sortedChildren.map((child) => (
        <TreeNodeComponent
          key={child.path}
          node={child}
          depth={0}
          selectedFileId={selectedFileId}
          onSelectFile={onSelectFile}
          defaultOpen={true}
        />
      ))}
    </div>
  );
}
