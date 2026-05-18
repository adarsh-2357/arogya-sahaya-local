import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { vscDarkPlus } from "react-syntax-highlighter/dist/esm/styles/prism";
import { ProjectFile } from "../data/projectFiles";
import { Copy, Check, FileCode2 } from "lucide-react";
import { useState } from "react";

interface CodeViewerProps {
  file: ProjectFile;
}

export default function CodeViewer({ file }: CodeViewerProps) {
  const [copied, setCopied] = useState(false);

  const handleCopy = async () => {
    await navigator.clipboard.writeText(file.content);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  const langMap: Record<string, string> = {
    kotlin: "kotlin",
    xml: "xml",
    properties: "properties",
  };

  const langBadgeColor =
    file.language === "kotlin"
      ? "bg-purple-500/20 text-purple-300 ring-purple-500/30"
      : file.language === "xml"
      ? "bg-orange-500/20 text-orange-300 ring-orange-500/30"
      : "bg-emerald-500/20 text-emerald-300 ring-emerald-500/30";

  const lineCount = file.content.split("\n").length;

  return (
    <div className="flex h-full flex-col">
      {/* Header */}
      <div className="flex items-center justify-between border-b border-slate-700/50 bg-slate-800/50 px-4 py-3 sm:px-6">
        <div className="flex items-center gap-3 min-w-0">
          <span className="text-xl shrink-0">{file.icon}</span>
          <div className="min-w-0">
            <div className="flex items-center gap-2 flex-wrap">
              <h2 className="text-base font-semibold text-white truncate">
                {file.name}
              </h2>
              <span
                className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ring-1 ring-inset ${langBadgeColor}`}
              >
                {file.language.toUpperCase()}
              </span>
            </div>
            <p className="mt-0.5 text-xs text-slate-400 truncate">
              {file.path}
            </p>
          </div>
        </div>

        <div className="flex items-center gap-3 shrink-0 ml-4">
          <span className="hidden sm:inline-flex items-center gap-1 text-xs text-slate-500">
            <FileCode2 className="h-3 w-3" />
            {lineCount} lines
          </span>
          <button
            onClick={handleCopy}
            className={`flex items-center gap-1.5 rounded-lg px-3 py-1.5 text-xs font-medium transition-all ${
              copied
                ? "bg-emerald-500/20 text-emerald-300"
                : "bg-slate-700 text-slate-300 hover:bg-slate-600 hover:text-white"
            }`}
          >
            {copied ? (
              <>
                <Check className="h-3.5 w-3.5" />
                Copied!
              </>
            ) : (
              <>
                <Copy className="h-3.5 w-3.5" />
                Copy
              </>
            )}
          </button>
        </div>
      </div>

      {/* Description */}
      <div className="border-b border-slate-700/30 bg-slate-800/30 px-4 py-2 sm:px-6">
        <p className="text-sm text-slate-400">{file.description}</p>
      </div>

      {/* Code */}
      <div className="flex-1 overflow-auto">
        <SyntaxHighlighter
          language={langMap[file.language] || "text"}
          style={vscDarkPlus}
          showLineNumbers
          wrapLongLines
          customStyle={{
            margin: 0,
            padding: "16px",
            background: "transparent",
            fontSize: "13px",
            lineHeight: "1.6",
            minHeight: "100%",
          }}
          lineNumberStyle={{
            minWidth: "3em",
            paddingRight: "1em",
            color: "#475569",
            fontSize: "12px",
          }}
        >
          {file.content}
        </SyntaxHighlighter>
      </div>
    </div>
  );
}
