(ns dirscan.core
  (:gen-class)
  (:import
    (java.io File)
    (java.nio.file FileSystems FileSystem Files FileVisitor SimpleFileVisitor FileVisitResult)
    )

)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def def_fs (FileSystems/getDefault))
  (doseq [rd (. def_fs getRootDirectories)]
    (println (. rd toString))
    )

  (def visitor
    (proxy [java.nio.file.SimpleFileVisitor] []
      (preVisitDirectory [dir attrs]
        (println "Dir" (. dir toString))
        java.nio.file.FileVisitResult/CONTINUE
      )
      (visitFile [file attrs]
        (println "File" (. file toString) (. attrs size))
        java.nio.file.FileVisitResult/CONTINUE
      )
    )
  )

  (def here (new File "."))
  (println (. here getAbsolutePath))
  (println (. here toPath))
  (println "Visitor is " visitor)
  (Files/walkFileTree (. here toPath) visitor)

  (println "Hello, World!")
  )

(-main)
