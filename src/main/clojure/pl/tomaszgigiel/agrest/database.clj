(ns pl.tomaszgigiel.agrest.database
  (:require [clojure.java.jdbc :as jdbc])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:import java.io.PrintWriter)
  (:import java.net.InetAddress)
  (:import org.apache.derby.drda.NetworkServerControl)
  (:gen-class))

(defn create-in-memory-derby-database
  ([name user password] 
    (create-in-memory-derby-database user password 1527))
  ([name user password port]
    (let [db-spe {:connection-uri (format "jdbc:derby:memory:%1$s;create=true;user=%2$s;password=%3$s" name user password)}
          server (NetworkServerControl. (InetAddress/getByName "localhost") port user password)]
      (do (Class/forName "org.apache.derby.jdbc.ClientDriver")
        (.start server (PrintWriter. *out* true))))))

(defn shutdown-in-memory-derby-database
  ([] (shutdown-in-memory-derby-database 1527))
  ([port]
    (let [server (NetworkServerControl. (InetAddress/getByName "localhost") port)]
      (try (.shutdown server) (catch Exception e "not necessary")))))

(defn -main
  "agrest: database multitool, database"
  [& args]
  (log/info "ok (agrest: database multitool, database)"))
