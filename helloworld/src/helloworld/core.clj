(ns helloworld.core
  (:gen-class))

(defn lists 
  "mostly work with lists"
  []
  (let [mylist (list 1 2 3 4 5 6 1 2 3 4 5 6)]
    (println mylist)
    (println (list* 1 2 3 4  [12 13] ))
    (println (str "The list has " (count mylist) " items"))
    (println (set mylist))
  )
)

(defn bmi 
  "calculate the BMI"
  [height weight]  
)

(defn orimain 
  "the original main program"
  []
  (println "I'm a little teapot")
  (println "Short and stout")
  (loop [iter 0]
    (println (str "Iteration " iter))
    (if (> iter  9) 
      (println "Goodbye")
      (recur (inc iter))
    )
  )
  (println (format "G%03d" 324))

)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (lists)
  )
