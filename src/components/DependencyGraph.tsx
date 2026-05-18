interface Dependency {
  name: string;
  version: string;
  category: string;
  color: string;
}

const dependencies: Dependency[] = [
  { name: "Compose BOM", version: "2024.02.00", category: "UI", color: "from-blue-500 to-cyan-500" },
  { name: "Material3", version: "BOM", category: "UI", color: "from-violet-500 to-purple-500" },
  { name: "Navigation Compose", version: "2.7.7", category: "Navigation", color: "from-emerald-500 to-teal-500" },
  { name: "Room", version: "2.6.1", category: "Data", color: "from-amber-500 to-orange-500" },
  { name: "Hilt", version: "2.48.1", category: "DI", color: "from-red-500 to-rose-500" },
  { name: "WorkManager", version: "2.9.0", category: "Background", color: "from-indigo-500 to-blue-500" },
  { name: "MPAndroidChart", version: "3.1.0", category: "Charts", color: "from-pink-500 to-fuchsia-500" },
  { name: "DataStore", version: "1.0.0", category: "Data", color: "from-yellow-500 to-amber-500" },
  { name: "Coroutines", version: "1.7.3", category: "Async", color: "from-teal-500 to-green-500" },
  { name: "Lifecycle", version: "2.7.0", category: "Architecture", color: "from-cyan-500 to-sky-500" },
  { name: "KSP", version: "1.9.22", category: "Build", color: "from-slate-400 to-slate-600" },
  { name: "AGP", version: "8.2.2", category: "Build", color: "from-green-500 to-emerald-500" },
];

export default function DependencyGraph() {
  const categories = [...new Set(dependencies.map((d) => d.category))];

  return (
    <div className="p-4 sm:p-6 space-y-6">
      <div>
        <h3 className="text-lg font-semibold text-white mb-1">
          Dependency Overview
        </h3>
        <p className="text-sm text-slate-400">
          All libraries configured in the project with their versions
        </p>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-3">
        {dependencies.map((dep) => (
          <div
            key={dep.name}
            className="group relative overflow-hidden rounded-xl border border-slate-700/50 bg-slate-800/50 p-4 transition-all hover:border-slate-600 hover:bg-slate-800"
          >
            <div
              className={`absolute inset-x-0 top-0 h-0.5 bg-gradient-to-r ${dep.color}`}
            />
            <div className="flex items-start justify-between gap-2">
              <div>
                <h4 className="font-medium text-white text-sm">{dep.name}</h4>
                <p className="mt-1 text-xs text-slate-500">{dep.category}</p>
              </div>
              <span className="shrink-0 rounded-md bg-slate-700/50 px-2 py-0.5 text-xs font-mono text-slate-300">
                {dep.version}
              </span>
            </div>
          </div>
        ))}
      </div>

      <div className="flex flex-wrap gap-2 pt-2">
        {categories.map((cat) => (
          <span
            key={cat}
            className="rounded-full bg-slate-700/50 px-3 py-1 text-xs text-slate-400 ring-1 ring-slate-600/50"
          >
            {cat}
          </span>
        ))}
      </div>
    </div>
  );
}
