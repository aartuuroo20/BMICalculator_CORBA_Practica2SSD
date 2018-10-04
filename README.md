## change directory to src/

```sh
$ cd src/
```

## compile java files (inside bmicalculator and BMICalculatorApp folders)

```sh
$ javac bmicalculator/*.java BMICalculatorApp/*.java
```

## start orbd

```sh
$ orbd -ORBInitialPort 1050&
```

## start BMICalculatorServer (inside bmicalculator package)

```sh
$ java bmicalculator.BMICalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost&
```

## start BMICalculatorClient (inside bmicalculator package)

```sh
$ java bmicalculator.BMICalculatorClient -ORBInitialPort 1050 -ORBInitialHost localhost
```
