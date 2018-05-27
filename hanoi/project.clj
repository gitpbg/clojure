(defproject hanoi "0.1.0-SNAPSHOT"
  :description "Towers of Hanoi"
  :url "http://gharpure.com"
  :license {:name "Gharpure Public License"
            :url "http://www.gharpure.com"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot hanoi.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
