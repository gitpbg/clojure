(ns nqueens.core
  (:gen-class))
;
; board will be represented by tuples of x and y
; ((0, 0), (1, 1)) means 2 queens, one at 0, 0 the other at 1, 1 (not a valid combination)
;


(def boardsize 8)
(def solutions 0)
(def tries 0)
(def rowcol (range boardsize))
(def queen "Q")
(def blank ".")

(defn newboard []
  (vec (repeat (* boardsize boardsize) "."))  
)

(def board (newboard))


(defn printboard_old []
  (doseq [item 
      (map clojure.string/join (partition boardsize board))
    ]
    (println item)
  )
)

(defn redbrd [brd qn]
  (let [ pos (+ (second qn) (* boardsize (first qn)))]
    (assoc brd pos "Q")
  )
)

(defn printboard [brd]
  (doseq [item  (partition boardsize (reduce redbrd (vec (repeat (* boardsize boardsize) ".")) brd))]
    (println item)
  )
)

(declare cankill?)
(declare safepos?)

(defn getsol [brd r]
  (doseq [c (range boardsize)]
    (def tries (inc tries))
    (if (safepos? brd (list r c))
      (if (= (inc r) boardsize)
        (do 
          (def solutions (inc solutions))
          (println (conj brd (list r c)))  
          (printboard (conj brd (list r c))))
        (getsol (conj brd (list r c)) (inc r))
       )
    )
  )  
)


(defn safepos? [brd q]
    (every? false? (map #(cankill? q %) brd)) 
)

(defn cankill? [q1 q2]
  (let [[q1r q1c] q1 [q2r q2c] q2]
    (cond
      (= q1r q2r) true
      (= q1c q2c) true
      (= (Math/abs (- q1r q2r)) (Math/abs (- q1c q2c))) true
      :else false
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def solutions 0)
  (def tries 0)
  (println "Enter the size of the board.  Valid values are between 4 and 9 ")
  (def boardsize (Integer/parseInt (read-line)))
  (getsol () 0)
  (println solutions "Unique solutions")
  (println tries "postions explored")
  'done
)

