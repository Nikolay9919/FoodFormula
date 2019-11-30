package com.example.foodformula.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodformula.ApiConnection.ApiFunctions
import com.example.foodformula.ApiConnection.Event
import com.example.foodformula.ApiConnection.NetworkService
import com.example.foodformula.ApiConnection.ResponseWrapperRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: ApiFunctions = NetworkService.retrofitService()

    // У нас будут две базовые функции requestWithLiveData и
    // requestWithCallback, в зависимости от ситуации мы будем
    // передавать в них лайвдату или колбек вместе с параметрами сетевого
    // запроса. Функция принимает в виде параметра ретрофитовский suspend запрос,
    // проверяет на наличие ошибок и сетит данные в виде ивента либо в
    // лайвдату либо в колбек. Про ивент будет написано ниже

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> ResponseWrapperRecipes<T>) {

        // В начале запроса сразу отправляем ивент загрузки
        liveData.postValue(Event.loading())

        // Привязываемся к жизненному циклу ViewModel, используя viewModelScope.
        // После ее уничтожения все выполняющиеся длинные запросы
        // будут остановлены за ненадобностью.
        // Переходим в IO поток и стартуем запрос
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                // Сетим в лайвдату командой postValue в IO потоке
                if (response.data != null) liveData.postValue(Event.success(response.data[0]))
                else if (response.error != null) liveData.postValue(Event.error(response.error))
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <T> requestWithCallback(
        request: suspend () -> ResponseWrapperRecipes<T>,
        response: (Event<T>) -> Unit) {

        response(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                // здесь все аналогично, но полученные данные
                // сетим в колбек уже в главном потоке, чтобы
                // избежать конфликтов с
                // последующим использованием данных
                // в context классах
                launch(Dispatchers.Main) {
                    if (res.data != null) response(Event.success(res.data[0]))
                    else if (res.error != null) response(Event.error(res.error))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // UPD (подсказали в комментариях) В блоке catch ивент передаем тоже в Main потоке
                launch(Dispatchers.Main) {
                    response(Event.error(null))
                }
            }
        }
    }
}