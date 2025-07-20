/*package com.ferreteria.martillazo.service;

import com.ferreteria.martillazo.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final List<Producto> allProducts = new ArrayList<>();
    private final Map<String, String> categoryTitles = new HashMap<>();
    private final Map<String, String> categoryDescriptions = new HashMap<>();

    public DataService() {
        // Inicializa los datos de los productos
        allProducts.addAll(Arrays.asList(
            new Product("hm1", "Martillo de Garra (Mango de Fibra)", 25.00, "https://cahema.pe/14398-large_default/martillo-carpintero-mango-de-fibra-24-oz-gy-hm-5407-goodyear.webp", "herramientas-manuales", "Herramienta para el mercado profesional que la distingue por su gran calidad en producto y empaque donde busca que el consumidor trabaje mejor, de manera inteligente y con mayor rapidez. El prestigio de la marca Goodyear ahora en las mejores herramientas para el hogar y la industria. El cromo-vanadio es estructuralmente fuerte, y su dureza permite soportar la fatiga alta y el desgaste.", "https://cahema.pe/14398-large_default/martillo-carpintero-mango-de-fibra-24-oz-gy-hm-5407-goodyear.webp", true), // 'true' indica que es un producto destacado
            
            new Product("hm2", "Destornillador Phillips #2", 12.50, "https://shopdelta.eu/shop_image/product/st-0-64-932_d.jpg", "herramientas-manuales", "Destornillador Bimaterial Punta Phillips Nuevo mango bimaterial ergonómico Diseño de mango más robusto que se adapta mejor a la mano y que permite incrementar considerablemente el torque Mango de dos componentes: Amarillo de alto impacto y negro antiderrapante Barra fabricada de acero aleado con doble tratamiento térmico", null, false),
            
            new Product("hm3", "Llave Inglesa Ajustable 8\"", 18.50, "https://mvelectronica.s3.us-east-2.amazonaws.com/productos/PET-8X/6101965608d8a.webp", "herramientas-manuales", "Llave ajustable cromada de 8 pulgadas con mango de goma para un agarre cómodo y seguro. Herramienta versátil para ajustar y apretar tuercas y pernos de diferentes tamaños.", null, false),
            
            new Product("hm4", "Juego de Llaves Allen (9 piezas)", 35.00, "https://cahema.pe/46164-large_default/juego-de-9-llaves-allen-hexagonales-15563-truper.webp", "herramientas-manuales", "Set completo de llaves Allen de 9 piezas, fabricadas en acero de alta resistencia. Incluye las medidas más comunes para diversos usos en ensamblaje y mantenimiento. Con estuche organizador.", null, false),
            
            new Product("hm5", "Cinta Métrica 5m", 9.90, "https://cfrouting.zoeysite.com/cdn-cgi/image/format=auto,fit=scale-down,quality=70/https://s3.amazonaws.com/zcom-media/sites/a0i0h00000QJocmAAD/media/catalog/product/a/r/arquivos-ids-156789-30-615.jpg", "herramientas-manuales", "Cinta métrica retráctil de 5 metros, con carcasa de goma resistente a impactos. Ideal para mediciones precisas en construcción, carpintería y hogar. Clip para cinturón incluido.", null, false),

            new Product("c1", "Bolsa de Cemento Sol (42.5kg)", 55.00, "https://promart.vteximg.com.br/arquivos/ids/7133522-1000-1000/22662.jpg?v=638228745030330000", "construccion", "Cemento Portland tipo I, ideal para obras generales de construcción. Alta resistencia y fraguado rápido. Saco de 42.5 kg para proyectos de mediana y gran escala.", null, false),
            
            new Product("c2", "Ladrillo King Kong (Unidad)", 1.20, "https://promart.vteximg.com.br/arquivos/ids/8550337-1000-1000/17700.jpg?v=638727531457370000", "construccion", "Ladrillo de arcilla de alta resistencia, ideal para muros y tabiques. Cumple con los estándares de construcción para garantizar seguridad y durabilidad. Dimensiones estándar para fácil manejo.", null, false),
            
            new Product("c3", "Arena Fina (Saco 25kg)", 15.00, "https://static.erpcloud.info/storage/5AB29D924DC100F694D895DD5D7A8F0C/PRODUCT/53BB806D134907FFFBBD109C8BF90619/AVATAR/thumb/saco-arena-fina-25-kg-60000002305@0300x0300.png", "construccion", "Saco de arena fina lavada, para acabados, morteros y revoques. Libre de impurezas, ideal para trabajos de albañilería fina y preparación de mezclas homogéneas.", null, false),
            
            new Product("c4", "Calaminas (Unidad)", 30.00, "https://promart.vteximg.com.br/arquivos/ids/571164-1000-1000/37329.jpg?v=637401129766500000", "construccion", "Láminas de calamina galvanizada, resistentes a la corrosión y aptas para techos y coberturas. Proporcionan protección contra la intemperie y son fáciles de instalar. Ligeras y duraderas.", null, false),

            new Product("cp1", "Clavos de Acero con Cabeza (1kg)", 8.00, "https://cdn3.alegra.com/66161a4cc0a3df10cd9fb75ebaccfcc30b2e7eb4-1617591388-clavo-2.png?Expires=1747330079&Signature=HjzbszGwyAEpVOHv5riQ9ohak10J8-ZdrdI2Jy6vAHP7upfvioPQOSNyuq31w-VO9lKJMkxLYsVjOgMKvUkiqrrXrjnQ97bN6avCv6YIjO5VHGHyya1XZZflq6FsIWcKmWXdBCtCNdwJiiDymncUkp-qw6As~5bSPp9RBW3OLWyT4gvHMIdCxu~9kjvaSy4pWZe83tRANYtmiq-JxHiMCSsz1NpzYIIt6flaj7nOq70ScXDavlVqVbHGNHZMw8RbbpTHFJyXbAvKur00n0F7b~mDcQUH5r-rUREJ89uEs9Nho7H5FTy3ttUmJ8Gc23Y7ui~uY8e0LsmiWc-t-9fTMA__&Key-Pair-Id=APKAJU3VE62QBWZP27QQ", "clavos-y-pernos", "Clavos de acero con cabeza plana, ideales para fijaciones en madera. Alta resistencia a la flexión y tracción. Presentación en bolsa de 1 kg para diversos proyectos.", null, false),
            new Product("cp2", "Tornillos Autoperforantes (Caja 100u)", 18.00, "https://promart.vteximg.com.br/arquivos/ids/425400-1000-1000/20571.jpg?v=637231960078500000", "clavos-y-pernos", "Caja de 100 tornillos autoperforantes, ideales para fijación en metal y madera sin necesidad de perforación previa. Punta afilada y rosca especial para un agarre firme.", null, false),
            new Product("cp3", "Pernos Hexagonales con Tuerca (Unidad)", 2.50, "https://http2.mlstatic.com/D_NQ_NP_835338-MLU75010421299_032024-O.webp", "clavos-y-pernos", "Perno hexagonal de acero de alta resistencia con tuerca, para uniones estructurales y de alta carga. Disponible en varias medidas para adaptarse a diferentes aplicaciones.", null, false),
            new Product("cp4", "Tarugos Plásticos con Tope 8mm (Bolsa 50u)", 5.00, "https://http2.mlstatic.com/D_NQ_NP_2X_978460-MLC73479084104_122023-F.webp", "clavos-y-pernos", "Bolsa de 50 tarugos plásticos de expansión, perfectos para fijaciones ligeras en pared y concreto. Compatibles con tornillos de madera y tirafondos.", null, false),

            new Product("j1", "Tijeras de Podar (Bypass)", 45.00, "https://promart.vteximg.com.br/arquivos/ids/310994-1000-1000/114053.jpg?v=636957951095270000", "jardineria", "Tijeras de podar de tipo bypass para cortes limpios y precisos en ramas verdes. Mango ergonómico antideslizante para un uso cómodo y seguro. Hoja de acero de alta calidad.", null, false),
            new Product("j2", "Rastrillo para Hojas", 22.50, "https://lukat.pe/wp-content/uploads/2022/09/AnyConv.com__24-rastrillo-jardinero-truperpng-1647965651-min.webp", "jardineria", "Rastrillo ligero con mango largo, ideal para recoger hojas y residuos de jardín. Dientes de plástico flexibles y resistentes. Facilita la limpieza de grandes superficies de forma rápida.", null, false),
            new Product("j3", "Manguera de Jardín (15m)", 60.00, "https://cahema.pe/16183-large_default/manguera-bicapa-jardin-12-x-15m-conexion-plastica.webp", "jardineria", "Manguera expandible de 15 metros con boquilla de rociado ajustable. Se expande hasta 3 veces su tamaño original al llenarse de agua. Ligera, fácil de guardar y transportar. Incluye adaptadores.", null, false),
            new Product("j4", "Guantes de Jardinería", 15.00, "https://media.falabella.com/sodimacPE/4221389_01/w=1500,h=1500,fit=pad", "jardineria", "Guantes resistentes para proteger tus manos al trabajar en el jardín. Fabricados con material transpirable y palma reforzada para mayor agarre y durabilidad. Tallas disponibles para un ajuste perfecto.", null, false),
            new Product("j4", "Guantes de Jardinería", 15.00, "https://media.falabella.com/sodimacPE/4221389_01/w=1500,h=1500,fit=pad", "jardineria", "Guantes resistentes para proteger tus manos al trabajar en el jardín. Fabricados con material transpirable y palma reforzada para mayor agarre y durabilidad. Tallas disponibles para un ajuste perfecto.", null, false)
        ));

        // Títulos de categoría y descripción
        categoryTitles.put("index", "Ferretería Martillazo");
        categoryTitles.put("herramientas-manuales", "Herramientas Manuales");
        categoryTitles.put("clavos-y-pernos", "Clavos y Pernos");
        categoryTitles.put("construccion", "Productos de Construcción");
        categoryTitles.put("jardineria", "Jardinería");
        categoryTitles.put("nosotros", "Conoce a Ferretería Martillazo");
        categoryTitles.put("ofertas", "Ofertas Especiales");
        categoryTitles.put("preguntas-frecuentes", "Preguntas Frecuentes");


        categoryDescriptions.put("herramientas-manuales", "Explora nuestra amplia selección de herramientas manuales para todos tus proyectos.");
        categoryDescriptions.put("clavos-y-pernos", "Soluciones robustas para todas tus necesidades de fijación y anclaje.");
        categoryDescriptions.put("construccion", "Materiales esenciales para construir proyectos sólidos y duraderos.");
        categoryDescriptions.put("jardineria", "Todo lo necesario para cuidar y embellecer tu jardín.");
        categoryDescriptions.put("index", "Tu destino para herramientas y materiales de ferretería de calidad. ¡Descubre nuestra amplia gama de productos para cada proyecto!"); // Descripción para la página de inicio
    }

    public List<Producto> getProductsByCategory(String category) {
        if (category == null || category.isEmpty() || category.equalsIgnoreCase("index")) {
            return allProducts; // Devuelve todos los productos si no se especifica categoría o es "index"
        }
        return allProducts.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public String getCategoryTitle(String category) {
        return categoryTitles.getOrDefault(category, "Categoría Desconocida");
    }

    public String getCategoryDescription(String category) {
        return categoryDescriptions.getOrDefault(category, null); // Devuelve 'null' si no hay descripción
    }

    public Producto getProductById(String id) {
        return allProducts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    //Devuelve un producto destacado para mostrar en la sección hero de la página principal.
    //Por ahora, devuelve el primer producto que tenga 'isFeatured' como true.
    
    public Producto getHighlightedProduct() {
        return allProducts.stream()
                .filter(Product::isFeatured)
                .findFirst()
                .orElse(null); // Devuelve null si ningún producto está marcado como destacado
    }
}
*/