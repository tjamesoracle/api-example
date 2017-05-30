(ns api-example.core
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer [ok]]
            [org.httpkit.server :as server]
            [schema.core :as s]))

(def app
  (api
    {:swagger {:ui "/docs"
               :spec "/swagger.json"
               :data {:info {:title "Example API"
                             :description "An Example API"}
                      :tags [{:name "stuff" :description "lots of stuff"}]}}}
    (context "/" []
      :tags ["blah"]
      (GET "/hello" []
        :return String
        :query-params [{name :- String nil}]
        :summary "Say hello"
        (ok (str "Hello, " (or name "stranger")))))))

(defn -main
  [& _]
  (server/run-server app {:port 8080}))
