package com.negoreserva.common.feature.concrete.category.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    HOSTING(
            // o que é reservado/comprado
            List.of(
                    "Quarto Simples",
                    "Quarto Duplo",
                    "Quarto Twin",
                    "Suite",
                    "Suite Presidencial",
                    "Chalé",
                    "Apartamento",
                    "Bungalow",
                    "Tenda",
                    "Casa Completa"
            ),
            // o que é descrito/filtrado na apresentação

            
            List.of(
                    // Instalações do quarto
                    "Casa de Banho Privativa",
                    "Casa de Banho Partilhada",
                    "Cozinha",
                    "Kitchenette",
                    "Varanda",
                    "Terraço",
                    "Sala de Estar",
                    // Equipamentos
                    "Ar Condicionado",
                    "Aquecimento",
                    "Wi-Fi",
                    "Televisão",
                    "Cofre",
                    "Minibar",
                    "Frigorífico",
                    // Cama / capacidade
                    "Cama King Size",
                    "Cama Queen Size",
                    "Camas Separadas",
                    "Beliche",
                    // Vista / localização
                    "Vista para o Mar",
                    "Vista para a Piscina",
                    "Vista para o Jardim",
                    "Andar Alto",
                    // Serviços
                    "Pequeno-Almoço Incluído",
                    "Serviço de Quarto",
                    "Limpeza Diária",
                    "Acessível para Mobilidade Reduzida"
            )
    ),

    RESTAURANT(
            List.of(
                    "Prato Principal",
                    "Entrada",
                    "Sobremesa",
                    "Bebida",
                    "Menu do Dia",
                    "Menu Infantil",
                    "Buffet",
                    "Petisco",
                    "Prato Vegetariano",
                    "Prato Vegano"
            ),
            List.of(
                    // Dieta / restrições
                    "Vegetariano",
                    "Vegano",
                    "Sem Glúten",
                    "Sem Lactose",
                    "Halal",
                    "Kosher",
                    // Tipo de cozinha
                    "Cozinha Angolana",
                    "Cozinha Africana",
                    "Cozinha Internacional",
                    "Cozinha Portuguesa",
                    "Grelhados",
                    "Frutos do Mar",
                    // Ambiente / serviço
                    "Esplanada",
                    "Espaço Privado",
                    "Música ao Vivo",
                    "Reserva de Mesa",
                    "Takeaway",
                    "Entrega ao Domicílio",
                    "Self-Service",
                    // Características do prato
                    "Picante",
                    "Para Partilhar",
                    "Prato do Chef",
                    "Promoção do Dia"
            )
    );

    private final List<String> products;
    private final List<String> tags;
}