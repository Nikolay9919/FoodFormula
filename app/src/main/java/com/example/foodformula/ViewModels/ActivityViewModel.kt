package com.example.foodformula.ViewModels

import androidx.lifecycle.MutableLiveData
import com.example.foodformula.ApiConnection.Event
import com.example.foodformula.ApiConnection.Models.Recipe

class ActivityViewModel : BaseViewModel() {

    // Создаем лайвдату для нашего списка юзеров
    val simpleLiveData = MutableLiveData<Event<Recipe>>()

    // Получение юзеров. Обращаемся к функции  requestWithLiveData
    // из BaseViewModel передаем нашу лайвдату и говорим,
    // какой сетевой запрос нужно выполнить и с какими параметрами
    // В данном случае это api.getUsers
    // Теперь функция сама выполнит запрос и засетит нужные
    // данные в лайвдату
    fun getRandomRecipe() {
        requestWithLiveData(simpleLiveData) {
            api.getRandomRecipe()
        }
    }

    fun getRecipeByIngridient(ingredient: String, callback: (data: Event<List<Recipe?>>?) -> Unit) {
        requestWithCallback({
            api.getRecipeByIngridient(
                ingredient
            )
        }) {
            callback(it)
        }
    }

    fun getRecipeById(id: Int) {
        requestWithLiveDataWithoutWraper(simpleLiveData) {
            api.getRecipeById(id)
        }
    }

    // Здесь аналогично, но вместо лайвдаты используем котлиновский колбек
    // UPD Полученный результат мы можем обработать здесь перед отправкой во вью
//    fun getUsersError(page: Int, callback: (data: Event<Recipe>) -> Unit) {
//        requestWithCallback({
//            api.getRandomRecipe()
//        }) {
//            callback(it)
//        }
//    }
}