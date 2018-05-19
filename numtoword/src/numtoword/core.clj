(ns numtoword.core
  (:gen-class)
)

(def words {
  0 'Zero  1 'One 2 'Two 3 'Three 4 'Four 5 'Five 6 'Six
  7 'Seven 8 'Eight 9 'Nine 10 'Ten 11 'Eleven 12 'Twelve
  13 'Thirteen 14 'Fourteen 15 'Fifteen 16 'Sixteen 17 'Seventeen
  18 'Eighteen 19 'Nineteen 20 'Twenty 30 'Thirty 40 'Forty
  50 'Fifty 60 'Sixty 70 'Seventy 80 'Eighty 90 'Ninety
  100 'Hundred 1000 'Thousand 1000000 'Million
})
(def DEBUG false)
(def MILLION 1000000)
(def BILLION (* MILLION 1000))
(def TRILLION (* BILLION 1000))

(defn numtoword 
  "convert number to word"
  [x]
  (when DEBUG (println "Got " x))
  (cond 
    (< x 21)  (get words x)
    (contains? words x) (get words x)
    (< x 100) (str (numtoword (* (quot x 10) 10)) " " (numtoword (mod x 10)))
    (< x 1000) (str (numtoword (quot x 100)) " Hundred and " (numtoword (mod x 100)))
    (< x MILLION) (str (numtoword (quot x 1000)) " Thousand " (numtoword (mod x 1000)))
    (< x BILLION) (str (numtoword (quot x MILLION)) " Million " (numtoword (mod x MILLION)))
    (< x TRILLION) (str (numtoword (quot x BILLION)) " Billion " (numtoword (mod x BILLION)))
    :else (str "Not implemented")
  )
)

(defn -main
  "Main entry point"
  [& args]
)