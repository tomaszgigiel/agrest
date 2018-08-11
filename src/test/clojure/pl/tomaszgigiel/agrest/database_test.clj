(ns pl.tomaszgigiel.agrest.database-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.agrest.database :as agrest])
  (:require [pl.tomaszgigiel.agrest.database-test-config :as mytest]))

(use-fixtures :once mytest/once-fixture)
(use-fixtures :each mytest/each-fixture)

(deftest databse-test
  (testing "database first" 
           (is (= nil (agrest/create-in-memory-derby-database "agrest-test" "agrest-test-user" "agrest-test-password")))))

(deftest databse-1527-test
  (testing "database first" 
           (is (= nil (agrest/create-in-memory-derby-database "agrest-test" "agrest-test-user" "agrest-test-password" 1527)))))

(deftest databse-other-port-test
  (testing "database first" 
           (is (= nil (agrest/create-in-memory-derby-database "agrest-test" "agrest-test-user" "agrest-test-password" 1234)))))
