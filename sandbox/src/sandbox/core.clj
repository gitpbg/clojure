(ns sandbox.core
  (:gen-class))

(defn printvec "print a vector"
  [vec]
  (println (str "vec " vec " has " (count vec) " entries"))
)

(defn setvars "Set variables" []
  (def CON1 "Constant One")
  (def CON2 "Constant Two")
)

(defn printvars "print variables" []
  (println "Hello")
  (println (str "CON1 = " CON1))
  (println (str "CON2 = " CON2))
)
 

(defn vectors 
  "practice vector methods"
  []
  (let [myvec ["hello " "world " "from " "prasad " "gharpure" ]]
    (printvec myvec)
    (println (reduce str (map #(clojure.string/upper-case %) myvec)))
    (println (nth myvec 2))
    (println (first myvec))
    (println (last myvec))
    (println (take 2 myvec))
    (println (drop 2 myvec))
    (println (map count myvec))
  )
)

(defn write-n-times "write a number ntimes" [n greet]
  (println n)
  (loop [x n] (println (str x "=>" greet)) (if (> x 0 ) (recur (dec x))))
  )
(defn maps 
  "practice maps"
  []
  (let [mymap {:first "Hello" :second "World"}]
    (map #(list (first %) (second %)) mymap)
  )
)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (vectors)
  
  )
