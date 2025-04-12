package assignment;

import java.util.Scanner;

public class RPG {
	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money;
	static int monster_level, monster_power, monster_hp, monster_defense, monster_mp, monster_experience, monster_money;
	static String hero_name, monster_name;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("영웅의 이름을 입력하세요. : ");
		hero_name = in.next();
		hero_level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_experience = 0;
		hero_money = 0;

		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		System.out.println("*********************");
		System.out.printf("현재 Hero 의 이름 : %s\n", hero_name);
		System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
		System.out.printf("현재 %s의 힘 : %d\n", hero_name, hero_power);
		System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
		System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
		System.out.printf("현재 %s의 경험치 : %d\n", hero_name, hero_experience);
		System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
		System.out.println("*********************");

		while (hero_hp != 0 || monster_hp != 0) {

			System.out.println("사냥터에 입장하였습니다.");
			System.out.println("1. 너구리 \n2. 상쾡이");
			System.out.print("전투할 상대를 입력하세요. : ");
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				monster_name = "너구리";
				monster_hp = 100;
				monster_mp = 0;
				monster_level = 1;
				monster_power = 20;
				monster_defense = 5;
				monster_money = 10;
				monster_experience = 10;

				System.out.println("너구리와 전투를 시작합니다.");
				System.out.printf("%s의 공격입니다.\n", hero_name);
				System.out.printf("너구리 데미지는 %d 입니다.\n", hero_attack());

				if (hero_attack() >= monster_hp) {
					System.out.println("너구리가 죽었습니다.");
					hero_experience += monster_experience;
					hero_money += monster_money;
					break;
				}

			case 2:
				monster_name = "살쾡이";
				monster_hp = 2000;
				monster_mp = 0;
				monster_level = 5;
				monster_power = 200;
				monster_defense = 20;
				monster_money = 30;
				monster_experience = 50;

			}
			System.out.println("*********************");
			System.out.printf("현재 Hero 의 이름 : %s\n", hero_name);
			System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
			System.out.printf("현재 %s의 힘 : %d\n", hero_name, hero_power);
			System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
			System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
			System.out.printf("현재 %s의 경험치 : %d\n", hero_name, hero_experience);
			System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
			System.out.println("*********************");

		}
	}

	static int hero_attack() {
		int sum;
		sum = hero_level * 10 + hero_power * 30;
		return sum;

	}

	static void hero_attacked(int sum) {
		if (hero_defense < monster_power)
			hero_hp = hero_hp + hero_defense - monster_power;

	}

	static int monster_attack() {
		int sum;
		sum = monster_level * 10 + monster_power * 30;
		return sum;
	}

	static void monster_attacked(int sum) {
		if (monster_defense < sum)
			monster_hp = monster_hp + monster_defense - sum;

	}
	/*
	 * static int potionStore_show(int monwy, int num) {
	 * 
	 * }
	 */

}
