// 질문 데이터
const qnaData = [
    { id: 1, category: "예약 관련", question: "예약은 어떻게 하나요?", answer: "홈페이지에서 원하는 날짜와 시간을 선택하여 예약할 수 있습니다. 예약 확인은 이메일로 발송됩니다." },
    { id: 2, category: "예약 관련", question: "당일 예약도 가능한가요?", answer: "네, 당일 예약은 잔여 시간이 있는 경우 가능합니다. 홈페이지에서 확인해 주세요." },
    { id: 3, category: "예약 관련", question: "예약 변경이나 취소는 어떻게 하나요?", answer: "예약 변경이나 취소는 예약 확인 이메일에 포함된 링크를 통해 가능합니다. 또는 고객센터로 문의해 주세요." },
    { id: 4, category: "예약 관련", question: "예약 없이 방문해도 이용할 수 있나요?", answer: "가능하지만, 예약 손님 우선으로 운영되므로 대기가 필요할 수 있습니다." },
    { id: 5, category: "운영 시간 및 요금", question: "운동장 운영 시간은 어떻게 되나요?", answer: "운동장은 오전 9시부터 오후 8시까지 운영됩니다. 계절에 따라 변동될 수 있으니 확인해 주세요." },
    { id: 6, category: "운영 시간 및 요금", question: "이용 요금은 얼마인가요?", answer: "강아지 1마리 기준 1시간에 10,000원이며, 추가 시간당 5,000원이 부과됩니다. 패키지 요금제도 확인해 보세요." },
    { id: 7, category: "운영 시간 및 요금", question: "할인 혜택이 있나요?", answer: "네, 회원 가입 시 첫 방문 할인 혜택이 있으며, 정기 이용 고객을 위한 특별 요금제도 준비되어 있습니다." },
    { id: 8, category: "시설 관련", question: "소형견과 대형견은 따로 구분되나요?", answer: "네, 소형견과 대형견이 안전하게 뛰어놀 수 있도록 별도의 구역이 마련되어 있습니다." },
    { id: 9, category: "시설 관련", question: "운동장 내 시설은 어떤 것들이 있나요?", answer: "슬라이드, 터널, 점프 장애물, 물놀이 공간 등 다양한 놀이 시설이 마련되어 있습니다." },
    { id: 10, category: "시설 관련", question: "강아지 전용 화장실도 있나요?", answer: "네, 운동장 내에 강아지 전용 화장실이 마련되어 있습니다." },
    { id: 11, category: "이용 규칙", question: "강아지 예방접종 증명이 필요한가요?", answer: "네, 모든 강아지는 기본 예방접종을 완료한 상태여야 하며, 증명서를 지참해 주세요." },
    { id: 12, category: "이용 규칙", question: "강아지가 공격적인 경우에도 이용 가능한가요?", answer: "안전을 위해 공격 성향이 있는 강아지는 입장이 제한될 수 있습니다. 사전에 상담해 주세요." },
    { id: 13, category: "이용 규칙", question: "간식이나 음식을 가져갈 수 있나요?", answer: "네, 가능합니다. 단, 다른 강아지와 분쟁을 방지하기 위해 음식은 구역 내에서 주의 깊게 관리해 주세요." },
    { id: 14, category: "이용 규칙", question: "강아지가 리드줄 없이 뛰어놀 수 있나요?", answer: "네, 운동장 내에서는 리드줄 없이 자유롭게 뛰어놀 수 있습니다." },
    { id: 15, category: "추가 서비스", question: "강아지 돌봄 서비스도 제공되나요?", answer: "네, 보호자가 잠시 자리를 비우는 동안 강아지를 돌봐주는 서비스가 있습니다(별도 요금 적용)." },
    { id: 16, category: "추가 서비스", question: "운동장 외에 다른 서비스도 있나요?", answer: "네, 운동장 외에도 강아지 목욕 시설, 미용 서비스, 카페 등이 준비되어 있습니다." },
    { id: 17, category: "안전 및 위생", question: "운동장의 위생 상태는 어떤가요?", answer: "모든 시설은 매일 청소되며, 주기적으로 소독하여 청결을 유지하고 있습니다." },
    { id: 18, category: "안전 및 위생", question: "사고 발생 시 어떻게 하나요?", answer: "사고 발생 시 현장 스태프가 즉시 도움을 드리며, 응급 처치 키트가 준비되어 있습니다." },
    { id: 19, category: "안전 및 위생", question: "다른 강아지와 다툼이 발생하면 어떻게 하나요?", answer: "스태프가 즉시 개입하여 상황을 관리하며, 보호자와 협의하여 문제를 해결합니다." },
    { id: 20, category: "기타", question: "주차장은 있나요?", answer: "네, 고객 전용 무료 주차장이 마련되어 있습니다." },
    { id: 21, category: "기타", question: "어린아이도 동반 가능한가요?", answer: "가능합니다. 하지만 강아지와의 안전을 위해 어린아이를 주의 깊게 돌봐주세요." },
    { id: 22, category: "기타", question: "강아지가 없는 사람도 방문할 수 있나요?", answer: "네, 강아지가 없는 고객도 방문할 수 있으며, 강아지 친구들과 교감할 수 있습니다." }
];


let currentPage = 1;
let rowsPerPage = 5;
let filteredData = [...qnaData]; // 검색 및 필터링된 데이터

function renderTable() {
    const tableBody = document.getElementById("qnaList");
    const start = (currentPage - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageData = filteredData.slice(start, end);

    tableBody.innerHTML = "";
    pageData.forEach((q) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${q.id}</td>
            <td>${q.category}</td>
            <td class="question">${q.question}</td>
        `;
        row.classList.add("clickable");
        row.addEventListener("click", () => toggleAnswer(q.id, row));
        tableBody.appendChild(row);
    });

    renderPagination();
}

function toggleAnswer(id, row) {
    const existingAnswerRow = document.querySelector(`.answer[data-id="${id}"]`);
    if (existingAnswerRow) {
        existingAnswerRow.remove(); // 답변이 이미 표시된 경우 숨기기
    } else {
        const questionData = qnaData.find((q) => q.id === id);
        const answerRow = document.createElement("tr");
        answerRow.classList.add("answer");
        answerRow.setAttribute("data-id", id);
        answerRow.innerHTML = `
            <td colspan="3" class="answer-cell">${questionData.answer}</td>
        `;
        row.after(answerRow);
    }
}

function renderPagination() {
    const totalPages = Math.ceil(filteredData.length / rowsPerPage);
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    // 이전 페이지 버튼
    if (currentPage > 1) {
        const prevButton = document.createElement("button");
        prevButton.textContent = "«";
        prevButton.addEventListener("click", () => {
            currentPage--;
            renderTable();
        });
        pagination.appendChild(prevButton);
    }

    // 페이지 번호 버튼
    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement("button");
        button.textContent = i;
        if (i === currentPage) button.classList.add("active");
        button.addEventListener("click", () => {
            currentPage = i;
            renderTable();
        });
        pagination.appendChild(button);
    }

    // 다음 페이지 버튼
    if (currentPage < totalPages) {
        const nextButton = document.createElement("button");
        nextButton.textContent = "»";
        nextButton.addEventListener("click", () => {
            currentPage++;
            renderTable();
        });
        pagination.appendChild(nextButton);
    }
}

function searchQuestions() {
    const searchTerm = document.getElementById("searchInput").value.toLowerCase();
    filteredData = qnaData.filter((q) =>
        q.question.toLowerCase().includes(searchTerm) ||
        q.category.toLowerCase().includes(searchTerm)
    );
    currentPage = 1; // 검색 시 첫 페이지로 이동
    renderTable();
}

function changeRowsPerPage() {
    rowsPerPage = parseInt(document.getElementById("itemsPerPage").value);
    currentPage = 1; // 페이지 수 변경 시 첫 페이지로 이동
    renderTable();
}

// 이벤트 리스너 추가
document.getElementById("searchInput").addEventListener("input", searchQuestions);
document.getElementById("itemsPerPage").addEventListener("change", changeRowsPerPage);

// 초기 렌더링
renderTable();
