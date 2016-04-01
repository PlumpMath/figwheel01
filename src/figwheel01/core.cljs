(ns ^:figwheel-always figwheel01.core
  (:require [goog.dom :as dom]))

(enable-console-print!)

(println "Page loading...")

(defonce app-state (atom 0))                                ;; define your app data so that it doesn't get over-written on reload

(add-watch app-state :console #(-> %4 clj->js js/console.log))

(def ctx2d (.getContext (dom/getElement "js_canvas") "2d"))

(defn on-js-reload []
  (swap! app-state inc))                                    ;; optionally touch app-state to call add-watch[ers]

(reset! app-state @app-state)                               ;; touch appstate

(defn init-canvas! [h w]
  (let [canvas (dom/getElement "js_canvas")]
    (set! (.-height canvas) h)
    (set! (.-width canvas) w)))

(defn draw2d! []
  (set! (.-fillStyle ctx2d) "#FA6900")
  (set! (.-shadowOffsetX ctx2d) @app-state)
  (set! (.-shadowOffsetX ctx2d) @app-state)
  (set! (.-shadowBlur ctx2d) 4)
  (set! (.-shadowColor ctx2d) "rgba(204, 204, 204, 0.5)")
  (.fillRect ctx2d 0 0 125 150))

(init-canvas! 600 800)

(draw2d!)