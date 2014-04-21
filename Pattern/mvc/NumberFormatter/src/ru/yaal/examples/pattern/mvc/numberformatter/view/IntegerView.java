package ru.yaal.examples.pattern.mvc.numberformatter.view;

/**
 * Представление числа в виде целого.
 */
public class IntegerView extends AbstractView {
    @Override
    protected String formatNumber(double number) {
        return String.valueOf(new Double(number).intValue());
    }
}
