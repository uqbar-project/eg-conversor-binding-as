# Conversor Mobile con Data Binding

## Enunciado

El [enunciado](http://algo3.uqbar-project.org/material/ejemplos/dominios/conversor) plantea algunas variantes.

## El proyecto 

Este proyecto está generado en Java para

* Android Studio 2.2 (Octubre 2016)
* con Gradle 2.14.1 (el que viene con Android Studio)
* para una SDK 24 (Nougat)

## Arquitectura MVC

![grafico](/image/Android_Conversor_Binding.png)

* La **vista** se define como un xml
* El **controller** lo define el ConversorActivity
* El **view model** es un ConversorModel, que define los bindings contra la vista y tiene 
 * una propiedad kilómetros read-only de tipo *String* 
 * la propiedad millas de tipo *String*
 * el mensaje convertir que delega al conversor la actualización de los kilómetros y notifica ese cambio a la vista
* El **domain model** es un objeto Conversor, con
 * propiedades kilómetros y millas que son numéricas
 * la responsabilidad de convertir de millas a kilómetros
 
 ## Configuraciones necesarias para tener binding
 
 * Tenés que usar gradle 1.5.0 ó superior (en el ejemplo usamos gradle 2.2.0)
 * El archivo [app/build.gradle](/app/build.gradle) -el de la aplicación, no el del proyecto- debe tener estas líneas
 
 ```json
 android {
     ...
     dataBinding {
        enabled = true
    }
}
 ```
 
 * En la vista tienen que usar un *view model* definiendo un tag data...
 
```xml
    <data>
        <variable
            name="conversor"
            type="ar.edu.uqbar.conversor_binding.ConversorModel" />
    </data>
```

 * ... y luego se puede utilizar el mecanismo de Data Binding. En el caso del TextView que muestra los kilómetros, el binding es one-way, se demarca de la siguiente manera:

```xml
        <TextView
            ...
            android:text="@{conversor.kilometros}" />
```

 * ...en el caso del EditText que permite ingresar las millas, queremos que se two-way el binding, para que la vista actualice el modelo y viceversa. Entonces debemos agregar el símbolo = entre el @ y el {:
 
```xml
        <EditText
            ...
            android:text="@={conversor.millas}" />
```

 * El binding de eventos se da construyendo un closure que define el comportamiento para el botón:
 
 ```xml
         <Button
            ...
            android:onClick="@{() -> conversor.convertir()}"
            android:text="Convertir" />
 ```
