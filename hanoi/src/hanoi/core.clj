(ns hanoi.core
  (:gen-class))

(def pins {:one '() :two '() :three '()})


(defn makeboard 
    [n]
    {
      :one (range 1 (inc n))
      :two (list)
      :three (list)
    }
)

(defn movedisk [f t]
  (let [src (pins f) dest (pins t)]
    (def pins
      (assoc
        (assoc pins f (rest src))
        t (conj dest (first src))
      )
    )
  )
) 

(defn movetower [n from to via]
  (if (= n 0) 
    (do
;      (println "depth" n "moving disk" from " to " to) 
      (movedisk from to) 
      (println "depth " n pins)
      )
    (do
      (movetower (dec n) from via to)
      (movedisk from to)
      (println "depth " n pins)
      (recur (dec n) via to from)
    )
  )
)

; (defn movedisk "move disk from px to py"
;   [board x y]
;   (let [src (x board) dest (y board)]
;     (println x y src dest)
;     (assoc 
;       (assoc board x (pop src)) 
;         y 
;         (conj dest (last src))
;     )
;   )
; )


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hell, World!")
  (println board)
  (def board (movedisk board :one :two))
  (println board)
  (def board (movedisk board :two :three))
  (println board)
)
