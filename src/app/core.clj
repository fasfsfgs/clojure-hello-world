(ns app.core
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.util.response :refer [resource-response content-type response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.adapter.jetty :as jetty]
            [clojure.java.jdbc :as db]
            [environ.core :refer [env]]))

; DATABASE STUFF
(def db-url (env :database-url "postgres://localhost:5432/testdb"))

(defn query-list [query]
  (println (db/query db-url query))
  (db/query db-url query))

(defn record [input]
  (db/insert! db-url
              :sayings {:content input}))

(defn db-test []
  (response
   (query-list ["select ps.id, ps.description, ps.active from payment_source ps order by ps.id asc"])))

(defroutes appRoutes
  (GET "/test" []
       (db-test))
  (GET "/" []
       (content-type (resource-response "index.html" {:root "public"}) "text/html"))
  (route/resources "/")
  (route/not-found (slurp (io/resource "404.html"))))

(def app
  (-> appRoutes
      wrap-json-response
      (wrap-defaults api-defaults)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port 5000)))]
    (jetty/run-jetty app {:port port :join? false})))
