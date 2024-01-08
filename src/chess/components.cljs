(ns chess.components
  (:require [reagent.core :as reagent :refer [atom]]))

(defn fen-form-component [current-fen is-fen-valid? set-game-to-fen! append-fen-and-move-fen-pointer!]
  (let [local-fen (atom current-fen)]
    (fn []
      [:form.fen-form {:on-submit
                       #(do (.preventDefault %)
                            (when (is-fen-valid? @local-fen)
                              (set-game-to-fen! @local-fen)
                              (append-fen-and-move-fen-pointer!)))}
       [:input {:type :text
                :name :fen
                :value @local-fen
                :on-change #(reset! local-fen (-> % .-target .-value))}]
       [:button {:class "white-bg" :type :submit} "fen"]])))

(defn info-page [{:keys [current-fen is-fen-valid? set-game-to-fen! append-fen-and-move-fen-pointer! draws
                         current-winner result turn castling->fen-castling castling
                         is-in-check halfmove fullmove b w
                         en-passant-target->fen-en-passant en-passant-target]}]
  [:div.info-page
   [:div.fen-container
    [fen-form-component current-fen is-fen-valid? set-game-to-fen! append-fen-and-move-fen-pointer!]]
   [:ul
    [:li "wins:" [:ul [:li "white: " w] [:li "black: " b]]]
    [:li "draws: " draws]
    [:li "current-winner: " current-winner]
    [:li "result: " result]
    [:li "turn: " turn]
    [:li "castle availability: " (castling->fen-castling castling)]
    [:li "en passant: " (en-passant-target->fen-en-passant en-passant-target)]
    [:li "is-in-check: " is-in-check]
    [:li "halfmove: " halfmove]
    [:li "fullmove: " fullmove]]])
