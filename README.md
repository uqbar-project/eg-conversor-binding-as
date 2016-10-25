# Conversor Mobile con Data Binding

## Enunciado

El [enunciado](http://algo3.uqbar-project.org/material/ejemplos/dominios/conversor) plantea algunas variantes.

## El proyecto 

Este proyecto está generado en Java para

* Android Studio 2.2 (Octubre 2016)
* con Gradle 2.14.1 (el que viene con Android Studio)
* para una SDK 24 (Nougat)

## Arquitectura MVC

![grafico](https://drive.google.com/file/d/0B9t2msYh_ogZU3oyS3pWUkdYMnc/view?usp=sharing)

* La **vista** se define como un xml
* El **controller** lo define el ConversorActivity
* El **view model** es un ConversorModel, que define los bindings contra la vista y tiene 
 * una propiedad kilómetros read-only de tipo *String* 
 * la propiedad millas de tipo *String*
 * el mensaje convertir que delega al conversor la actualización de los kilómetros y notifica ese cambio a la vista
* El **domain model** es un objeto Conversor, con
 * propiedades kilómetros y millas que son numéricas
 * la responsabilidad convertir
 


