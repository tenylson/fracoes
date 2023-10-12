# fracoes TRABALHO SCRUM

Resumo do Capítulo 7 - Kata de Frações - do livro Software Craft

Por favor, deixe-me saber se você precisa de mais informações sobre o conteúdo do artigo.


Divida

Descubra como o trabalho pode ser dividido. Aqui, a divisão é meio evidente: as quatro operações. Cada uma das quatro operações está no mesmo nível de abstração. Se você começar a trabalhar em uma operação, deve manter o foco nela.


Prioritize

Por complexidade - Comece pela operação que parece mais simples (aqui a adição).
Pergunte ao cliente - Talvez o cliente precise da divisão como prioridade, pergunte a eles.


Explorar

Vamos supor que desejamos focar na adição.
Agora queremos explorar o problema, discutindo com o cliente para eliminar qualquer mal-entendido e coletar dados para nossos testes.
Exemplos de frações (apenas inteiros, números reais, números imaginários). => numerador e denominador são inteiros (incluindo negativos).
Exemplo de adições (mais complexas possíveis, as mais fáceis possíveis, mais prováveis).
Adição com mais de dois fatores?
Casos especiais ou regras das quais devo estar ciente? => denominadores não nulos, 0 no numerador implica 0 para a fração, se numerador e denominador forem iguais, a fração é 1.
Como você deseja ver o resultado? (simplificado apenas, tanto simplificado quanto bruto, ponto flutuante).
Como simplificar e o que isso significa? => MDC (Máximo Divisor Comum).


Regras de Negócio

Agora temos muitas regras de negócio, exemplos e critérios de aceitação para a adição:
   - 0 como uma fração.
   - 1 como uma fração.
   - Números inteiros como frações.
   - Denominadores iguais.
   - Denominadores diferentes.
   - Frações negativas.
   - 0 como um denominador.
   - Simplificação.

Agora, você pode explorar as outras operações ou começar a trabalhar na adição usando a metodologia TDD (Desenvolvimento Orientado a Testes).


TDD (Test-Driven Development)

Comece com um teste que falha - O primeiro teste é "Zero mais zero deve retornar zero". Seu teste deve seguir a organização/afirmação/execução, mas deve ser construído na ordem inversa Execução/Afirmação/Organização.
Use o código mínimo possível para fazer o teste passar.
	Realize um commit.
Agora, você pode voltar ao passo um, introduzindo apenas um novo conceito de cada vez.
Você pode precisar criar um segundo teste necessário que falhe para implementar a simplificação usando o MDC (Máximo Divisor Comum) e, para implementá-lo, você pode usar o TDD de forma cega ou encontrar algoritmos existentes ou verificar as capacidades da sua linguagem.


Refatoração

Usando a linguagem do domínio, você pode definir:
   - Subtração = adição com a fração oposta.
   - Divisão = multiplicação com o inverso da fração.

'Oposta' e 'inverso' podem ser definidos como métodos públicos; assim, o código fica mais legível e utiliza a linguagem do domínio.
