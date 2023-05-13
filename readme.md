<p align = "center">МИНИСТЕРСТВО НАУКИ И ВЫСШЕГО ОБРАЗОВАНИЯ<br>
РОССИЙСКОЙ ФЕДЕРАЦИИ<br>
ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ БЮДЖЕТНОЕ<br>
ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ ВЫСШЕГО ОБРАЗОВАНИЯ<br>
«САХАЛИНСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ»</p>
<br><br><br><br><br><br>
<p align = "center">Институт естественных наук и техносферной безопасности<br>Кафедра информатики<br>Сунагатов Денис Ринатович</p>
<br><br><br>
<p align = "center">Лабораторная работа №3<br><strong>"Жизненный цикл Activity"</strong><br>01.03.02 Прикладная математика и информатика</p>
<br><br><br><br><br><br><br><br><br><br><br><br>
<p align = "right">Научный руководитель<br>
Соболев Евгений Игоревич</p>
<br><br><br>
<p align = "center">г. Южно-Сахалинск<br>2022 г.</p>
<br><br><br><br><br><br><br><br><br><br><br><br>

# Введение

**Android Studio** — интегрированная среда разработки (IDE) для работы с платформой Android, анонсированная 16 мая 2013 года на конференции Google I/O. В последней версии Android Studio поддерживается Android 4.1 и выше.

**Kotlin** — это кроссплатформенный статически типизированный язык программирования общего назначения высокого уровня. Kotlin предназначен для полного взаимодействия с Java, а версия стандартной библиотеки Kotlin для JVM зависит от библиотеки классов Java, но вывод типов позволяет сделать ее синтаксис более кратким. Kotlin в основном нацелен на JVM, но также компилируется в JavaScript (например, для интерфейсных веб-приложений, использующих React) или собственный код через LLVM (например, для собственных приложений iOS, разделяющих бизнес-логику с приложениями Android). Затраты на разработку языка несет JetBrains, а Kotlin Foundation защищает торговую марку Kotlin.

# Цели и задачи

Задачи:

1. Предотвращение ввода нескольких ответов. После того как пользователь введет ответ на вопрос, заблокируйте кнопки этого вопроса, чтобы предотвратить возможность ввода нескольких ответов. 
2. Вывод оценки. После того как пользователь введет ответ на все вопросы, отобразите уведомление с процентом правильных ответов. 

# Решение

1. Добавил метод `updateCurrentQuestion` который будет обновлять Activity на основе данных отображаемого вопроса

```
fun updateCurrentQuestion() {
    question_view.setText(curQuestion.question)
    if (curQuestionIndex == 0) {
        prev_button.setEnabled(false)
    }
    else if (curQuestionIndex == questionArr.count()-1) {
        next_button.setEnabled(false)
    }
    else {
        prev_button.setEnabled(true)
        next_button.setEnabled(true)
    }        
    if (questionArr[curQuestionIndex].answered) {
        true_button.setEnabled(false)
        false_button.setEnabled(false)
    }
    else {
        true_button.setEnabled(true)
        false_button.setEnabled(true)
    }
}
```

2. В методе answer добавил строчку которая проверяет все ли вопросы были отвечены, и если да, то отображает процент правильных

```
if(allQuestionsAnswered()) {
    val ntotal: Int = questionArr.count()
    var ncorrect: Int = 0
    for(q in questionArr) {
        if(q.correct) {
            ncorrect += 1;
        }
    }
    val percentage = (100.0 * ncorrect.toFloat() / ntotal.toFloat()).roundToInt().toString();
    val str = "You answered " + percentage + "% of questions correctly";
    val toast_value = Toast.makeText(this, str, Toast.LENGTH_SHORT)
    toast_value.setGravity(Gravity.BOTTOM, 0, 0)
    toast_value.show()
}
```

# Вывод

..?