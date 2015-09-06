(ns app.core
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [camel-snake-kebab.core :as kebab]
            [environ.core :refer [env]]))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str ["Oii" :from 'fasfsfgs])})

(defroutes app
  (GET "/camel" {{input :input} :params}
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->camelCase input)})
  (GET "/snake" {{input :input} :params}
       (print-str input)
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->snake_case input)})
  (GET "/kebab" {{input :input} :params}
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body (kebab/->kebab-case input)})
  (GET "/" []
       (splash))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
