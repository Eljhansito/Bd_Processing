# Bd_Processing

# Spark & Scala — Resumen de Funcionalidades

Este repositorio contiene la resolución de varios ejercicios utilizando **Apache Spark** y **Scala**, trabajando con **DataFrames** y **RDDs** para procesar y analizar datos.


## 🛠️ Tecnologías y conceptos utilizados

- **Scala**
- **Apache Spark**
  - SparkSession
  - DataFrames
  - RDDs
  - Transformaciones y acciones
  - Joins
  - Agregaciones
  - UDFs (User Defined Functions)
  - Funciones de Spark SQL (`filter`, `select`, `groupBy`, `agg`, `orderBy`, `withColumn`, etc.)


## 📘 Ejercicios desarrollados

### **1. Operaciones básicas con DataFrames**
- Creación de un DataFrame con información de estudiantes.
- Visualización del esquema (*schema*).
- Filtrado de estudiantes con calificación mayor a 8.
- Ordenación por calificación y selección de nombres.


### **2. Uso de UDF**
- Definición de una función para determinar si un número es **par** o **impar**.
- Aplicación de la UDF sobre una columna de un DataFrame.


### **3. Joins y agregaciones**
- Unión entre un DataFrame de estudiantes y otro de calificaciones.
- Cálculo del **promedio de calificaciones por estudiante**.
- Ordenación del resultado final.


### **4. RDDs**
- Creación de un RDD a partir de una lista de palabras.
- Conteo de ocurrencias usando `map` + `reduceByKey`.


### **5. Procesamiento de archivos**
- Cálculo del **ingreso total por producto**:
  - Creación de nueva columna (`cantidad * precio_unitario`).
  - Agrupación por ID de producto.
  - Suma de ingresos.



