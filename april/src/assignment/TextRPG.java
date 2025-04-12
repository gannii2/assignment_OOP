package assignment;

import java.util.Scanner;

public class TextRPG {
	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money;
	static int monster_level, monster_power, monster_hp, monster_defense, monster_mp, monster_experience, monster_money;
	static String hero_name, monster_name;

	public static void heroInfo(String name) {
		hero_level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_experience = 0;
		hero_money = 0;
	}

	public static void printHeroInfo() {
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

	public static void monsterInfo(int choice) {
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
			break;

		case 2:
			monster_name = "살쾡이";
			monster_hp = 2000;
			monster_mp = 0;
			monster_level = 5;
			monster_power = 200;
			monster_defense = 20;
			monster_money = 30;
			monster_experience = 50;
			break;
		default:
			System.out.println("잘못된 선택입니다.");
			break;
		}
		;
	}

	// main
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("영웅의 이름을 입력하세요. : ");
		heroInfo(hero_name = in.next());
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		printHeroInfo();

		while (true) {
			printHeroInfo();
			System.out.println("1. 사냥터 입장 \n2. 상점 입장");
			System.out.print("입장할 장소를 선택하세요 : ");
			int where = in.nextInt();

			// 상점 입장
			int itemChoice;
			if (where == 2) {
				System.out.println("포션 상점에 입장하였습니다.");
				do {
					System.out.println("1. 힘 증강 포션 (30원)");
					System.out.println("2. 방어력 증강 포션 (30원)");
					System.out.println("3. 경험치 증강 포션 (100골드)");
					System.out.println("4. HP 증강 포션 (10골드)");
					System.out.println("5. MP 증강 포션 (10골드)");
					System.out.println("0. 나가기");
					System.out.print("원하시는 물건을 입력하세요: ");
					itemChoice = in.nextInt();

					if (itemChoice != 0) {
						hero_money = potionStore_show(hero_money, itemChoice);
						System.out.printf("%d원 남았습니다.\n", hero_money);
						level_up();
					}
				} while (itemChoice != 0); // 상점에서 나가기 선택할 때까지 구입 반복

				System.out.println("상점에서 나왔습니다.");
				printHeroInfo();
			}

			// 사냥터 입장
			System.out.println("사냥터에 입장하였습니다.");
			System.out.println("1. 너구리 (L1) \n2. 살쾡이 (L5)");
			System.out.print("전투할 상대를 입력하세요. : ");
			int who = in.nextInt();
			monsterInfo(who);
			System.out.printf("%s와 전투를 시작합니다. \n", monster_name);

			while (monster_hp > 0 && hero_hp > 0) {
				// 히어로의 공격
				System.out.printf("%s의 공격입니다. \n", hero_name);
				System.out.printf("%s의 데미지는 %d 입니다. \n", monster_name, hero_attack());
				monster_attacked(hero_attack());
				if (monster_hp <= 0)
					break;

				// 몬스터의 공격
				System.out.printf("%s의 공격입니다. \n", monster_name);
				System.out.printf("%s의 데미지는 %d 입니다. \n", hero_name, monster_attack());
				hero_attacked(monster_attack());
			}
			if (hero_hp <= 0) {
				System.out.printf("히어로 %s(이)가 죽었습니다...\n", hero_name);
				System.out.print("부활하시겠습니까? (1. Yes or 2. No) : ");
				int revival = in.nextInt();
				if (revival == 1) {
					System.out.printf("히어로 %s가 부활했습니다.\n", hero_name);
					System.out.printf("히어로 %s의 level이 1로 초기화됩니다.\n", hero_name);
					hero_level = 1;
					hero_power = 15;
					hero_defense = 25;
					hero_hp = 80;
					hero_experience = 0;
					hero_money = 0;
				} else {
					System.out.println("게임이 종료되었습니다.");
					break;
				}
			}
		}
	}

	static int hero_attack() {
		int sum = hero_level * 10 + hero_power * 30;

		return sum;
	}

	static void hero_attacked(int sum) {
		if (hero_defense < monster_power)
			hero_hp = hero_hp + hero_defense - monster_power;

	}

	static int monster_attack() {
		int sum = monster_level * 10 + monster_power * 30;

		return sum;
	}

	static void monster_attacked(int sum) {
		if (monster_defense < sum)
			monster_hp = monster_hp + monster_defense - sum;

		if (monster_hp <= 0) {
			System.out.printf("%s가 죽었습니다.\n", monster_name);
			hero_experience += monster_experience;
			hero_money += monster_money;

			level_up();
			printHeroInfo();
		} else {
			System.out.printf("남은 %s의 HP : %d \n", monster_name, monster_hp);
		}
	}

	static void level_up() {
		if (hero_experience >= hero_level * 80) {
			hero_level += 1;
			hero_money += hero_level * 50;
			System.out.printf("%s의 레벨이 %d가 되었습니다.\n", hero_name, hero_level);
			System.out.printf("레벨업 기념으로 돈이 %d 증가하여 %d원이 되었습니다. \n", hero_level * 50, hero_money);

		}
	}

	static int potionStore_show(int money, int num) {
		System.out.println("포션 상점에서 물건 구매를 시도하는 중입니다.");
		switch (num) {
		case 1:
			if (money >= 30) {
				hero_power += 3;
				System.out.println("힘이 +3 증가했습니다!");
				return money - 30;
			}
			break;
		case 2:
			if (money >= 30) {
				hero_defense += 3;
				System.out.println("방어력이 +3 증가했습니다!");
				return money - 30;
			}
			break;
		case 3:
			if (money >= 100) {
				hero_experience += 50;
				System.out.println("경험치가 +50 증가했습니다!");
				return money - 100;
			}
			break;
		case 4:
			if (money >= 10) {
				hero_hp += 50;
				System.out.println("HP가 +50 증가했습니다!");
				return money - 10;
			}
			break;
		case 5:
			if (money >= 10) {
				hero_mp += 50;
				System.out.println("MP가 +50 증가했습니다!");
				return money - 10;
			}
			break;

		default:
			System.out.println("잘못된 선택입니다.");
		}
		System.out.println("돈이 부족합니다. \n구입을 실패하였습니다.");
		return money;
	}

}
