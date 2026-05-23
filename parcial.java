import java.util.Scanner;

public class parcial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Gestión de consumo en comedor universitario");
        int tipos = 0;
        while (true) {
            System.out.print("¿Cuántos tipos de comida se ofrecerán? ");
            if (sc.hasNextInt()) {
                tipos = sc.nextInt();
                sc.nextLine();
                if (tipos > 0) break;
                System.out.println("Ingrese un número entero positivo.");
            } else {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                sc.nextLine();
            }
        }

        String[] nombreComida = new String[tipos];
        String[] tipo = new String[tipos];
        int[] cantidadDisponible = new int[tipos];
        int[] cantidadConsumida = new int[tipos];

        System.out.println("\n--- Registro de comidas ---");
        for (int i = 0; i < tipos; i++) {
            System.out.printf("Comida %d:\n", i + 1);
            System.out.print("  Nombre de la comida: ");
            nombreComida[i] = sc.nextLine().trim();
            while (nombreComida[i].isEmpty()) {
                System.out.print("  Nombre no puede estar vacío. Ingrese nombre: ");
                nombreComida[i] = sc.nextLine().trim();
            }

            System.out.print("  Tipo (ej. principal, postre, bebida): ");
            tipo[i] = sc.nextLine().trim();
            if (tipo[i].isEmpty()) tipo[i] = "Desconocido";

            int cant = -1;
            while (true) {
                System.out.print("  Cantidad disponible inicial: ");
                if (sc.hasNextInt()) {
                    cant = sc.nextInt();
                    sc.nextLine();
                    if (cant >= 0) break;
                    System.out.println("  Ingrese un número entero no negativo.");
                } else {
                    System.out.println("  Entrada inválida. Ingrese un número entero.");
                    sc.nextLine();
                }
            }
            cantidadDisponible[i] = cant;
            cantidadConsumida[i] = 0;
        }

        int estudiantes = 0;
        while (true) {
            System.out.print("\n¿Cuántos estudiantes asistirán al comedor? ");
            if (sc.hasNextInt()) {
                estudiantes = sc.nextInt();
                sc.nextLine();
                if (estudiantes >= 0) break;
                System.out.println("Ingrese un número entero no negativo.");
            } else {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                sc.nextLine();
            }
        }

        System.out.println("\n--- Atención a estudiantes ---");
        for (int e = 0; e < estudiantes; e++) {
            System.out.printf("\nEstudiante %d:\n", e + 1);
            System.out.print("  Nombre del estudiante: ");
            String nombreEst = sc.nextLine().trim();
            if (nombreEst.isEmpty()) nombreEst = "Estudiante" + (e + 1);

            System.out.print("  Comida deseada (nombre): ");
            String deseada = sc.nextLine().trim();
            while (deseada.isEmpty()) {
                System.out.print("  Ingrese el nombre de la comida deseada: ");
                deseada = sc.nextLine().trim();
            }
            int idx = -1;
            for (int i = 0; i < tipos; i++) {
                if (nombreComida[i].equalsIgnoreCase(deseada)) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1) {
                System.out.println("  La comida solicitada no existe en el menú.");
            } else {
                int disponibles = cantidadDisponible[idx] - cantidadConsumida[idx];
                if (disponibles > 0) {
                    cantidadConsumida[idx] += 1;
                    System.out.printf("  %s ha sido servido con %s. Quedan %d unidades.\n",
                            nombreEst, nombreComida[idx], cantidadDisponible[idx] - cantidadConsumida[idx]);
                } else {
                    System.out.printf("  Lo siento, %s está agotado.\n", nombreComida[idx]);
                }
            }
        }
        System.out.println("\n--- Resumen final ---");
        int totalServidos = 0;
        for (int i = 0; i < tipos; i++) {
            int restante = cantidadDisponible[i] - cantidadConsumida[i];
            if (restante < 0) restante = 0;
            System.out.printf("Comida: %s | Tipo: %s | Restante: %d | Consumida: %d\n",
                    nombreComida[i], tipo[i], restante, cantidadConsumida[i]);
            totalServidos += cantidadConsumida[i];
        }
        System.out.println("\nTotal general de comidas servidas: " + totalServidos);
        if (tipos > 0) {
            int idxMax = 0;
            int idxMin = 0;
            for (int i = 1; i < tipos; i++) {
                if (cantidadConsumida[i] > cantidadConsumida[idxMax]) idxMax = i;
                if (cantidadConsumida[i] < cantidadConsumida[idxMin]) idxMin = i;
            }
            System.out.printf("Comida con mayor consumo: %s (consumidas: %d)\n",
                    nombreComida[idxMax], cantidadConsumida[idxMax]);
            System.out.printf("Comida con menor consumo: %s (consumidas: %d)\n",
                    nombreComida[idxMin], cantidadConsumida[idxMin]);
        } else {
            System.out.println("No hay tipos de comida registrados.");
        }

        sc.close();
    }
}
