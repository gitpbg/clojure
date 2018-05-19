(ns numtoword.core-test
  (:require [clojure.test :refer :all]
            [numtoword.core :refer :all]))

(deftest a-test
  (testing "Zero converstion"
    (is (= 'Zero (numtoword 0)) "Zero test failed")
  )
)
