package homeworks.homework7.task2

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.View
import tornadofx.vbox
import tornadofx.label
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.combobox
import tornadofx.button
import tornadofx.action

class Menu : View() {
    val difficultyList: ObservableList<String> = FXCollections.observableArrayList("Easy", "Medium", "Hard")
    val selectedDifficulty = SimpleStringProperty()

    val playerSignList: ObservableList<String> = FXCollections.observableArrayList("X", "0")
    val selectedSign = SimpleStringProperty()

    override val root = vbox {
        addClass(MenuStyle.mainVbox)

        label("Tic-tac-toe (v2)") { addClass(MenuStyle.gameName) }

        hbox {
            addClass(MenuStyle.hboxForCombobox)

            vbox {
                addClass(MenuStyle.vboxForCombobox)

                label("Difficulty:").addClass(MenuStyle.descriptionLabel)

                combobox(selectedDifficulty, difficultyList) {
                    addClass(MenuStyle.combobox)
                    selectionModel.selectFirst()
                }
            }

            vbox {
                addClass(MenuStyle.vboxForCombobox)

                label("Your sign:").addClass(MenuStyle.descriptionLabel)

                combobox(selectedSign, playerSignList) {
                    addClass(MenuStyle.combobox)
                    selectionModel.selectFirst()
                }
            }
        }

        button("Play") {
            addClass(MenuStyle.playButton)
            action { replaceWith<GameField>() }
        }
    }
}
