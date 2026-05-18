import { projectFiles } from "../data/projectFiles";
import { FileCode2, Layers, Package, Shield } from "lucide-react";

export default function ProjectStats() {
  const totalFiles = projectFiles.length;
  const totalLines = projectFiles.reduce(
    (sum, f) => sum + f.content.split("\n").length,
    0
  );
  const kotlinFiles = projectFiles.filter(
    (f) => f.language === "kotlin"
  ).length;
  const gradleFiles = projectFiles.filter(
    (f) => f.category === "gradle"
  ).length;

  const stats = [
    {
      label: "Total Files",
      value: totalFiles,
      icon: FileCode2,
      color: "text-emerald-400",
      bg: "bg-emerald-500/10",
    },
    {
      label: "Lines of Code",
      value: totalLines,
      icon: Layers,
      color: "text-blue-400",
      bg: "bg-blue-500/10",
    },
    {
      label: "Kotlin Files",
      value: kotlinFiles,
      icon: Package,
      color: "text-purple-400",
      bg: "bg-purple-500/10",
    },
    {
      label: "Build Files",
      value: gradleFiles,
      icon: Shield,
      color: "text-amber-400",
      bg: "bg-amber-500/10",
    },
  ];

  return (
    <div className="grid grid-cols-2 gap-3 p-4 sm:grid-cols-4 sm:p-6">
      {stats.map((stat) => (
        <div
          key={stat.label}
          className="rounded-xl border border-slate-700/50 bg-slate-800/50 p-4 text-center"
        >
          <div
            className={`mx-auto mb-2 flex h-10 w-10 items-center justify-center rounded-lg ${stat.bg}`}
          >
            <stat.icon className={`h-5 w-5 ${stat.color}`} />
          </div>
          <div className="text-2xl font-bold text-white">{stat.value}</div>
          <div className="mt-0.5 text-xs text-slate-500">{stat.label}</div>
        </div>
      ))}
    </div>
  );
}
