# Use strong typed references to methods
from typing import Callable


def plus_one_func(a: int) -> int:
    return a + 1


def execute_func(func: Callable[[int], int], *args):
    return func(*args)


assert execute_func(plus_one_func, 2) == 3
