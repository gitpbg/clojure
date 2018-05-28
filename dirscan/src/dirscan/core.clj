(ns dirscan.core
  (:gen-class)
  (import
    java.io.File
    )
  )

(def fo (java.io.File. "."))

(println "fo = " (. fo getAbsolutePath))
(doseq [item (seq (. fo listFiles))]
  (cond
    (. item isDirectory) (println "Directory" (. item getCanonicalPath))
    (. item isFile) (println "File" (. item getCanonicalPath) (. item length))
    :else (println "Unknown " (. item getCanonicalPath))
    )
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  )
