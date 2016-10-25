# Conversor Mobile con Data Binding

## Enunciado

El [enunciado](http://algo3.uqbar-project.org/material/ejemplos/dominios/conversor) plantea algunas variantes.

## El proyecto 

Este proyecto está generado en Java para

* Android Studio 2.2 (Octubre 2016)
* con Gradle 2.14.1 (el que viene con Android Studio)
* para una SDK 24 (Nougat)

## Arquitectura MVC

![grafico](/images/Android_Conversor_Binding.png)

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
 
Tenés que usar gradle 1.5.0 ó superior (en el ejemplo usamos gradle 2.2.0).

### Configuración de gradle

El archivo [app/build.gradle](/app/build.gradle) -el de la aplicación, no el del proyecto- debe tener estas líneas
 
 ```
 android {
     ...
     dataBinding {
        enabled = true
    }
}
 ```

### Vista

En la [vista](app/src/main/res/layout/activity_conversor.xml) tienen que usar un *view model* definiendo un tag data...
 
```xml
    <data>
        <variable
            name="conversor"
            type="ar.edu.uqbar.conversor_binding.ConversorModel" />
    </data>
```
 
... el data debe estar dentro de un tag layout, que debe comenzar con minúscula (**IMPORTANTE** respetar esto ya que escribir Layout con mayúscula deriva en errores crípticos)
 
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
```
 
... y luego se puede utilizar el mecanismo de Data Binding. En el caso del TextView que muestra los kilómetros, el binding es one-way, se demarca de la siguiente manera:

```xml
        <TextView
            ...
            android:text="@{conversor.kilometros}" />
```

...en el caso del EditText que permite ingresar las millas, queremos que se two-way el binding, para que la vista actualice el modelo y viceversa. Entonces debemos agregar el símbolo = entre el @ y el {:
 
```xml
        <EditText
            ...
            android:text="@={conversor.millas}" />
```


El binding de eventos se da construyendo un closure que define el comportamiento para el botón:
 
 ```xml
         <Button
            ...
            android:onClick="@{() -> conversor.convertir()}"
            android:text="Convertir" />
 ```

### Controller

El [controller](app/src/main/java/ar/edu/uqbar/conversor_binding/ConversorActivity.java) define una Activity y genera el binding contra el view model correspondiente:

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        ActivityConversorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_conversor);
        ConversorModel conversor = new ConversorModel();
        binding.setConversor(conversor);
    }
```

### ViewModel

El modelo de la vista es un [ConversorModel](app/src/main/java/ar/edu/uqbar/conversor_binding/ConversorModel.java) que debe hacer algunas adaptaciones: debe extender de BaseObservable

```java
public class ConversorModel extends BaseObservable {
```

Las propiedades millas y kilómetros son bindeables, esto significa que disparan notificaciones a la vista (activity.xml):

```java
    @Bindable
    public String getMillas() {
        return "" + conversor.getMillas();
    }

    @Bindable
    public void setMillas(String millas) {
        try {
            conversor.setMillas(new BigDecimal(millas));
        } catch (NumberFormatException e) {
        }
    }

    @Bindable
    public String getKilometros() {
        return "" + conversor.getKilometros();
    }
```

El uso de Strings es necesario ya que el control de Android trabaja con este tipo de valores.
Internamente como vemos delega a un objeto de dominio conversor.

La conversión propiamente dicha debe refrescar la propiedad kilometros de solo lectura, entonces se dispara una notificación en forma manual, de la siguiente manera:

```java
    public void convertir() {
        conversor.convertir();
        notifyPropertyChanged(BR.kilometros);
    }
```

### Dominio

El objeto de dominio [Conversor](app/src/main/java/ar/edu/uqbar/conversor_binding/Conversor.java) simplemente define las propiedades kilómetros y millas y una funcionalidad de conversión básica.
