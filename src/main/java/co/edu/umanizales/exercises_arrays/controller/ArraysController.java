package co.edu.umanizales.exercises_arrays.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/arrays")
public class ArraysController {


    @GetMapping("/{num1}/{num2}")
    public String getPrimesBetweenTwoNumbers(@PathVariable int num1,
                                             @PathVariable int num2) {
        if (validateNumberPositive(num1) && validateNumberPositive(num2)) {
            if (isPrime(num1) && isPrime(num2)) {
                return "Ambos numeros son primos";
            } else if (isPrime(num2)) {
                return "el numero 2 es primo";
            } else if (isPrime(num1)) {
                return "el numero 1 es primo";

            } else {
                return "ninguno es primo";
            }
        } else {
            return "Los números deben ser positivos";
        }
    }


    @RestController
    @RequestMapping("/arrays/")
    public class NumeroController {




        /*/////////////////////////////////////////////////////// EJERCICIO 1//////////////////////////////////////////////////////// */


        @GetMapping("/positionmax/{number}")
        public String GetPositionMaximun(@PathVariable("number") String number) {
            // Convertir el parámetro de cadena a un arreglo de enteros
            String[] numbersArray = number.split(",");

            int[] arrays = new int[numbersArray.length];

            for (int i = 0; i < numbersArray.length; i++) {
                arrays[i] = Integer.parseInt(numbersArray[i].trim());
            }

            // Encontrar la posición del mayor número
            int positionMax = findPositionMaximun(arrays);


            return "La posición del mayor número en el arreglo es: " + positionMax;
        }



        /*/////////////////////////////////////////////////////// EJERCICIO 2//////////////////////////////////////////////////////// */


        //FUNCION PARA HALLAR EL MAXIMO NUMERO PRIMO QUE AHI EN EL ARREGLO

        @GetMapping("/maxPrimes/{numbers}")
        public String getNumberMaxPrime(@PathVariable("numbers") String numbers) {
            // Convertir el parámetro de cadena a un arreglo de enteros
            String[] numberArrays = numbers.split(",");

            int[] arrays = new int[numberArrays.length];
            int[] arraysPrimes = new int[numberArrays.length];

            for (int i = 0; i < numberArrays.length; i++) {
                int num = Integer.parseInt(numberArrays[i].trim());
                if (isPrime(num)) {
                    arraysPrimes[i] = Integer.parseInt(numberArrays[i].trim());
                }
            }


            // Encontrar la posición del mayor número
            int positionMax = findPositionMaximun(arraysPrimes);


            return "La posición del mayor número primo en el arreglo es: " + positionMax;
        }


        /*/////////////////////////////////////////////////////// EJERCICIO 3//////////////////////////////////////////////////////// */

        //  Almacenar en un arreglo de 10 posiciones los 10 números primos comprendidos entre 100 y 300.
        //  Luego mostrarlos en pantalla.

        @GetMapping("/PrimesBetweenNumbers/{num1}/{num2}")
        public String getNumbersPrimesBetween100and200(@PathVariable int num1,
                                                       @PathVariable int num2) {
            List<Integer> primos = new ArrayList<>();

            for (int i = num1; i <= num2 && primos.size() < 10; i++) {
                if (isPrime(i)) {
                    primos.add(i);
                }
            }

            // Convertir la lista a un arreglo
            int[] arregloPrimos = new int[primos.size()];
            for (int i = 0; i < primos.size(); i++) {
                arregloPrimos[i] = primos.get(i);
            }

            return "los numeros primos entre " + num1 + " y " + num2 + " es: " + Arrays.toString(arregloPrimos);


        }

        /*/////////////////////////////////////////////////////// EJERCICIO 4//////////////////////////////////////////////////////// */
        @PostMapping("/terminatedinfour")
        private String IsNumberTerminatedInFour(@RequestBody int[] array) {
            if (array.length>10){
                return "solo puede ingresar hasta 10 datos al arreglo";
            }
            boolean band = false;
            StringBuilder cadena = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                if (isModuleFour(array[i])) {
                    cadena.append("Posicion:").append(i).append(" Número:").append(array[i]).append(" \n");
                    band = true;
                }
            }
            if (!band) {
                return "No hay numeros terminados en 4";
            } else {
                return cadena.toString();
            }

        }

        /*/////////////////////////////////////////////////////// EJERCICIO 5//////////////////////////////////////////////////////// */
        @PostMapping("/canttimesmax")
        public String Cant_times_max(@RequestBody int[] array) {

            if (validateCantNumber(array)) {
            int max_number = 0;
            int cant_veces = 0;



            for (int i = 1; i < array.length; i++) {
                if (array[i] > max_number) {

                    max_number = array[i];

                }
            }
            for (int j = 0; j<array.length; j++) {

                if (array[j]==max_number) {
                    cant_veces++;
                }
            }

            return " el numero mayor de la lista es " + max_number + " y aparece " + cant_veces + " veces";
            }return "Solo puede ingresar hasta 10 datos al arreglo";
        }




        /*/////////////////////////////////////////////////////// EJERCICIO 6//////////////////////////////////////////////////////// */
        /* 6.	Leer 10 números enteros, almacenarlos en un arreglo y determinar si el promedio entero de estos datos está almacenado en el arreglo.*/

        @PostMapping("/promedioarray")
        public String PromedioInArray(@RequestBody int[] array) {
            if(validateCantNumber(array)) {
               int promedio = sumArray(array)/array.length;
               for (int i = 0; i < array.length; i++) {
                   if (array[i] == promedio) {
                       return "El promedio del arreglo es "+promedio+" y dicho valor se encuentra incluido en la lista";
                   }return "El valor del promedio no se encuentra en la lista";

               }
            }return "Solo puede ingresar hasta 10 datos al arreglo";
        }

        /*/////////////////////////////////////////////////////// EJERCICIO 7//////////////////////////////////////////////////////// */
        /*	Leer 10 números enteros, almacenarlos en un arreglo y determinar en qué posición está el número cuya suma de dígitos sea la mayor.*/


        @PostMapping("/sumdigits")
        private String sumaDigitsNumber(@RequestBody int[] array) {

            if (validateCantNumber(array)) {
                int suma = 0;
                int valor = 0;
                int listNumberSum[] = new int[array.length];
                for (int i = 0; i < array.length; i++) {
                    while (array[i] != 0) {
                        valor = array[i] % 10;
                        suma = suma + valor;
                        array[i] = array[i] / 10;
                    }
                    listNumberSum[i] = suma;
                    suma = 0;

                }
                findPositionMaximun(listNumberSum);

                return "Los digitos sumados de los numeros del arreglo son los siguientes"+Arrays.toString(listNumberSum)+" y el mayor de todos esta en la posicion "+ (findPositionMaximun(listNumberSum)+1);
            }


            return "Solo puede ingresar hasta 10 datos al arreglo";
        }




        /*/////////////////////////////////////////////////////// EJERCICIO 8//////////////////////////////////////////////////////// */
        /*8.	Leer 10 números enteros, almacenarlos en un arreglo y calcularle el factorial a cada uno de los números leídos almacenándolos en otro arreglo, luego mostrar en pantalla cada número con su factorial.*/

        @PostMapping("/factorials")
        public String calculateFactorials(@RequestBody int[] numbers) {

            long[] factorials = new long[numbers.length];

            String result = "";

            for (int i =0; i<numbers.length;i++) {
                int number = numbers[i];
                factorials[i] = calculateFactorial(number);
                result += number + ": " + calculateFactorial(number) + "\n";
            }
            return result;
        }

        /*/////////////////////////////////////////////////////// EJERCICIO 9//////////////////////////////////////////////////////// */
        /*9.	Leer 10 números enteros, almacenarlos en un arreglo y mostrar en pantalla todos los enteros comprendidos entre 1 y cada uno de los números almacenados en el arreglo.*/

        @PostMapping("/numbersrange")
        public int[][] getNumbersInRange(@RequestBody int[] numbers) {


            int[][] ranges = new int[numbers.length][];

            for (int i = 0; i < numbers.length; i++) {
                int number = numbers[i];
                ranges[i] = new int[number];
                for (int j = 0; j < number; j++) {
                    ranges[i][j] = j + 1;
                }
            }
            return ranges;
        }






    }

        //METODOS PARA EJECUTAR ALGUNAS FUNCIONALIDADES

        private boolean validateNumberPositive(int number) {
            if (number > 0) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isPrime(int number) {
            int contDiv = 0;
            int half = number / 2;
            for (int i = 2; i < half; i++) {
                if (number % i == 0) {
                    contDiv++;
                }
            }
            if (contDiv == 0) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isModuleFour(int number) {
            if (number % 10 == 4) {
                return true;
            } else {
                return false;
            }
        }


        // Función que encuentra la posición del mayor número en un arreglo
        private int findPositionMaximun(int[] arrays) {
            int maxNumber = arrays[0];
            int positionMax = 0;

            for (int i = 1; i < arrays.length; i++) {
                if (arrays[i] > maxNumber) {
                    maxNumber = arrays[i];
                    positionMax = i;
                }
            }

            return positionMax;
        }

        private Boolean validateCantNumber(int []array){
            if (array.length>10){
                return false;
            }

            return true;
        }

        private int sumArray(int[] array) {
        int suma = 0;
        for (int i = 0; i < array.length; i++) {
            suma += array[i];
        }
        return suma;
        }




    private long calculateFactorial(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;

    }








}