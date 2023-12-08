# Data Types
> https://www.w3schools.com/java/java_data_types.asp

## `Byte`
- MAX_VALUE = 127
- MIN_VALUE = -128
- SIZE = 8 Bits

## `Short`
- MAX_VALUE = 32,767
- MIN_VALUE = -32,768
- SIZE = 16 Bits

## `Integer`
- MAX_VALUE = 0x7FFF.FFFF
- MIN_VALUE = 0x8000.0000
- SIZE = 32 Bits
```text
MAX_VALUE = 2,147,483,647 (2^31 - 1)
MIN_VALUE = -2,147,483,648 (-2^31)
```

## `Long`
- MAX_VALUE = 0x7FFF.FFFF.FFFF.FFFF
- MIN_VALUE = 0x8000.0000.0000.0000
- SIZE = 64 Bits
```text
MAX_VALUE = 9,223,372,036,854,775,807L (2^63 - 1)
MIN_VALUE = -9,223,372,036,854,775,808L (-2^63)
```

## `Float`
- SIZE = 32 Bits
- Precision = 6 to 7 decimal digits
```text
Positive Value:
1. Biggest = 3.4028235E38f (MAX_VALUE)
2. Nearest Zero = 1.4E-45f (MIN_VALUE)

Negative Value:
1. Smallest = -3.4028235E38f
2. Nearest Zero = -1.4E-45f
```

## `Double`
- SIZE = 64 Bits
- Precision = 15 to 16 decimal digits
```text
Positive Value:
1. Biggest = 1.7976931348623157E308 (MAX_VALUE)
2. Nearest Zero = 4.9E-324 (MIN_VALUE)

Negative Value:
1. Smallest = -1.7976931348623157E+308
2. Nearest Zero = -4.9E-324
```

# Type Casting
> https://www.w3schools.com/java/java_type_casting.asp

## Widening Casting
(**automatically**) - converting a smaller type to a larger type size
```text
byte -> short -> char -> int -> long -> float -> double

int myInt = 9;
double myDouble = myInt;
```

## Narrowing Casting
(**manually**) - converting a larger type to a smaller size type
```text
double -> float -> long -> int -> char -> short -> byte

double myDouble = 9.78d;
int myInt = (int) myDouble;
```
------------------------------