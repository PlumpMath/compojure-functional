(ns compojure-func.core
  (:require [clojure.test :as t]))

(def ^:dynamic web-app nil)

(alias t/is assert!)

(defn assert-equal 
  "Helper method for assertions"
  [a b]
  (t/is (= a b)))

(defn request 
  "Makes a HTTP request call to a Compojure/Ring application"
  [app method resource & params]
  (app {:request-method method 
        :uri resource 
        :params (into [] params)}))

(defmacro with-app 
  [app & body]
  `(binding [web-app ~app]
     (do ~@body))) 
  
(defn assert-response
  "Assert a response matches a given status"
  [method url status]
    (let [response-code (:status (request web-app method url))]
      (assert-equal status response-code)))

(defn- contains-text 
  "Return boolen ture if a response body contains specified text"
  [body text]
  (boolean (re-find (re-pattern text) body)))

(defn assert-contains
  "Assert a response body contains specified text"
  [method url expected-text]
  (when-let [response-body (:body (request web-app method url))]
    (assert! (contains-text response-body expected-text))))

