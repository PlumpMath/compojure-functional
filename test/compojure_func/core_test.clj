(ns compojure-func.core-test
  (:require [clojure.test :refer :all]
            [compojure-func.web :as web]
            [compojure-func.core :as f]))

(def app web/app)

(deftest assert-response-test
  (testing "Response codes"
    (f/with-app app
      (f/assert-response :get "/" 200)
      (f/assert-response :get "/foobar" 404))))

(deftest contains-text-test
  (testing "Should return true if body matches text"
    (is (f/contains-text "Hello there sir" "sir"))))

(deftest assert-contains-test
  (testing "Returns true if body matches specified text"
    (f/with-app app
      (f/assert-contains :get "/" "Hello world"))))
