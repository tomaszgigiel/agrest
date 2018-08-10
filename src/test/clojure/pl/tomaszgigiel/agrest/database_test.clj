(ns pl.tomaszgigiel.agrest.database-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.agrest.database :as agrest]))

(deftest databse-test
  (testing "database first" (is (= 1 1))))
