(ns prodcons.core
  (:gen-class))


;;(def myagent (agent {:canwrite true :value 0 :canread false}))


; (defn do_producer [state]
;   (if (:canwrite state)
;     (do 
;       (println "Writing " (inc (:value state))) 
;       {:canwrite false :value (inc (:value state)) :canread true}
;     )
;     state
;   )
; )

; (defn do_consumer [state]
;   (if (:canread state)
;     (do 
;       (println "read " (:value state))
;       {:canwrite true :value (:value state) :canread false}
;     )
;     state
;   )
; )

; (defn producer_thread_fn []
;   (doseq [x (range 10)]
;     (println x)
;     (Thread/sleep 1000)
;   )
; )


;
; There is a vector 
; There is an agent that holds a value and boolean indicating last value
; Producer generates a value and sends off the agent to the consumer
; Consumer consumes the value and sends off the agent back to the producer
; There is a flag in the agent that indicate


(def pcagent (agent 0))

(declare consumer)

(defn producer [x]
  (println "Producer Thread " (.getId (Thread/currentThread)))
  (println "Producer Agent is " *agent*)
  (send pcagent consumer)
  (println (if (= x 10) "Producer not sending anything" (str "Producer sending " (inc x))))
  (if (< x 10) (inc x) nil)
)

(defn consumer [x]
  (println "Consumer Thread " (.getId (Thread/currentThread)))
  (if (nil? x)
    nil
    (do 
      (println "Consumer Agent is " *agent*)
      (println "Consumer got " x)
      (send pcagent producer)
      x
    )
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
