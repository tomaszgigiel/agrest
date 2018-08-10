(ns pl.tomaszgigiel.agrest.database
  (:require [clojure.java.jdbc :as jdbc])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:import java.io.PrintWriter)
  (:import java.net.InetAddress)
  (:import org.apache.derby.drda.NetworkServerControl)
  (:gen-class))

(defn -main
  "agrest: database multitool, database"
  [& args]
  (log/info "ok (agrest: database multitool, database)"))
