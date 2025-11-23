package examen_estructura

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.*

object Examen {

  /** Ejercicio 1: Crear un DataFrame y realizar operaciones básicas
   * Pregunta: Crea un DataFrame a partir de una secuencia de tuplas que contenga información sobre
   * estudiantes (nombre, edad, calificación).
   * Realiza las siguientes operaciones:
   *
   * Muestra el esquema del DataFrame.
   * Filtra los estudiantes con una calificación mayor a 8.
   * Selecciona los nombres de los estudiantes y ordénalos por calificación de forma descendente.
   */
  def ejercicio1(estudiantes: DataFrame)(implicit spark: SparkSession): DataFrame = {
    // 1. Mostrar esquema
    estudiantes.printSchema()

    // 2. Filtrar estudiantes con calificación > 8 y MOSTRAR por pantalla
    println("=== Estudiantes con calificación mayor a 8 ===")
    estudiantes
      .filter("calificacion > 8")
      .orderBy(desc("calificacion"))
      .show()

    estudiantes
      .select("nombre")
      .orderBy(desc("calificacion"))
  }

  /** Ejercicio 2: UDF (User Defined Function)
   * Pregunta: Define una función que determine si un número es par o impar.
   * Aplica esta función a una columna de un DataFrame que contenga una lista de números.
   */
  def ejercicio2(numeros: DataFrame)(implicit spark: SparkSession): DataFrame = {
    // Definir UDF que determina si un número es par o impar
    val esParImpar = udf((numero: Int) => {
      if (numero % 2 == 0) "Par" else "Impar"
    })

    // Aplicar la UDF a la columna "numero"
    numeros.select(esParImpar(col("numero")).alias("numero"))
  }

  /** Ejercicio 3: Joins y agregaciones
   * Pregunta: Dado dos DataFrames,
   * uno con información de estudiantes (id, nombre)
   * y otro con calificaciones (id_estudiante, asignatura, calificacion),
   * realiza un join entre ellos y calcula el promedio de calificaciones por estudiante.
   */
  def ejercicio3(estudiantes: DataFrame, calificaciones: DataFrame): DataFrame = {
    // Join entre estudiantes y calificaciones usando id = id_estudiante
    // Agrupar por id y nombre, calcular el promedio de calificaciones
    estudiantes
      .join(calificaciones, estudiantes("id") === calificaciones("id_estudiante"))
      .groupBy("id", "nombre")
      .agg(avg("calificacion").alias("calificacion"))
      .orderBy("id")
  }

  /** Ejercicio 4: Uso de RDDs
   * Pregunta: Crea un RDD a partir de una lista de palabras y cuenta la cantidad de ocurrencias de cada palabra.
   */
  def ejercicio4(palabras: List[String])(implicit spark: SparkSession): RDD[(String, Int)] = {
    val sc = spark.sparkContext

    // Crear RDD a partir de la lista
    val palabrasRDD = sc.parallelize(palabras)

    // Mapear cada palabra a (palabra, 1) y reducir por clave contando ocurrencias
    palabrasRDD
      .map(palabra => (palabra, 1))
      .reduceByKey(_ + _)
  }

  /**
   * Ejercicio 5: Procesamiento de archivos
   * Pregunta: Carga un archivo CSV que contenga información sobre
   * ventas (id_venta, id_producto, cantidad, precio_unitario)
   * y calcula el ingreso total (cantidad * precio_unitario) por producto.
   */
  def ejercicio5(ventas: DataFrame)(implicit spark: SparkSession): DataFrame = {
    // Calcular ingreso total por cada fila (cantidad * precio_unitario)
    // Agrupar por id_producto y sumar los ingresos
    ventas
      .withColumn("ingreso", col("cantidad") * col("precio_unitario"))
      .groupBy("id_producto")
      .agg(sum("ingreso").alias("ingreso_total"))
      .orderBy("id_producto")
  }

}
