(ns pl.tomaszgigiel.agrest.database-test-config
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.agrest.database :as agrest]))

(defn setup [] (do (agrest/shutdown-in-memory-derby-database) (agrest/shutdown-in-memory-derby-database 1234)))
(defn teardown [] (do (agrest/shutdown-in-memory-derby-database) (agrest/shutdown-in-memory-derby-database 1234)))

(defn once-fixture [f]
  (setup)
  (f)
  (teardown))

(defn each-fixture [f]
  (setup)
  (f)
  (teardown))
