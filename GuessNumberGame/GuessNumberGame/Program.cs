using System;

namespace GuessNumberGame
{
    class Program
    {
        static void Main(string[] args)
        {
            Random r = new Random();
            int num = r.Next(0, 100);
            Console.WriteLine("Guess a number between 0 and 100.");
            bool win = false;
            while (!win)
            {
                int guess = Convert.ToInt32(Console.ReadLine());
                if (guess == num)
                {
                    win = true;
                }
                else if (guess > num)
                {
                    Console.WriteLine("The number is so big. guess a smaller one");
                }
                else
                {
                    Console.WriteLine("The number is so small. guess a bigger one.");
                }
            }
            Console.WriteLine("That's correct!");
        }
    }
}
