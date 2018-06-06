(ns sandbox.core
  (:gen-class)
  (:require [clojure.data.csv :as csv]
    [clojure.java.io :as io]
  )
)


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

(defn iseven? 
  "custom debug predicate"
  [idx val]
  (println "index " idx "value" val)
  (when (even? idx) val)
)

(defn test-keep-indexed 
  "testing for keep indexed. Used to prune a sequence"
  []
 (println "Keep indexed will allow filtering on index")
 (def myvec (vec (range 10)))
 (println myvec)
 (println (keep-indexed iseven? myvec))
)

(defn test-for-assoc
  "testing for assoc and dissoc.  Used to modify a hash or vector (assoc only)"
  []
  (def myvec (vec (range 10)))
  (println "define a vector with range 10")
  (println myvec)
  (println "change element 0 to 20")
  (println (assoc myvec 0 20))
  (println "define a dictionary with :first 20 :second 30 :third 40")
  (def mydict {:first 20 :second 30 :third 40})
  (println mydict)
  (println "assoc a new element :fourth 50")
  (println (assoc mydict :fourth 50))
  (println "dissoc :second")
  (println (dissoc mydict :second))
)

(def headers [])
(def thedata [])
(def columns 
  (map keyword [
    "COUNT PARTICIPANTS"
    "COUNT FEMALE"
    "COUNT MALE"
    "COUNT AMERICAN INDIAN"
    ]
  )
)

(defn test-csv-read 
  "Sample code to read a csv file"
  []
  (let [
;    filename "/Users/gharpure/throwaway/data/Demographic_Statistics_By_Zip_Code.csv"
     filename "./Demographic_Statistics_By_Zip_Code.csv"
    ]
    (println filename)
    (with-open [reader (io/reader filename)]
      (let [csvr (csv/read-csv reader)]
        (def headers (map keyword (first csvr)))
        (def row (apply create-struct headers))
        (def thedata (map 
          (fn [therow] (apply struct row (seq therow)))
          (rest csvr)
        ))
        (println (count thedata) "records")
        (println (first thedata))
        (doseq [kw columns]
          (println kw (reduce 
            (fn [a b] 
              (+ a (Integer/parseInt (b kw)))
          ) 0 thedata))
        )
      )
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
;  (vectors)
  (test-csv-read) 
  )
