(defproject helloworld "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot helloworld.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})


;;  {:user {:plugins  [[cider/cider-nrepl "0.15.1"]]
;;       :dependencies [[org.clojure/tools.nrepl "0.2.12"]
;;                      [cljfmt "0.5.7"]]}}