(ns prodcons.core
  (:gen-class))


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
  (if (< x 10) (inc x) (println "producer sending nil"))
)

(defn consumer [x]
  (println "Consumer Thread " (.getId (Thread/currentThread)))
  (if (nil? x)
    (do (println "Consumer x is now nil") (shutdown-agents) nil)
    (do 
      (println "Consumer Agent is " *agent*)
      (println "Consumer got " x)
      (send pcagent producer)
      x
    )
  )
)

(defn -main
  "Kick off producer"
  [& args]
  (println "Starting producer")
  (send pcagent producer)
  (println "Done")
)