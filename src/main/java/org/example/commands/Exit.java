package org.example.commands;
/**
 * Команда завершения работы программы.
 * Не выполняет сохранение данных, только выход.
 */
public class Exit extends Command{
    public Exit(){
        super("exit");
    }
    /**
     * Выводит сообщение о завершении и завершает работу программы.
     * Реальное завершение должно быть реализовано в основном цикле
     *
     * @param args аргументы команды (игнорируются)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Работа завершена");
    }
}
