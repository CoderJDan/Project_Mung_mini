const notices = [
    { title: " ", description: " ", image: "/mung/images/notice/image1.jpg" },
    { title: " ", description: " ", image: "/mung/images/notice/image2.jpg" },
    { title: " ", description: " ", image: "/mung/images/notice/image3.jpg" },
];

// 카드 렌더링 함수
function renderNotices() {
    const container = document.getElementById('notice-container');
    container.innerHTML = ''; // 기존 내용 초기화

    notices.forEach((notice) => {
        container.innerHTML += `
            <div class="notice-container-wrapper">
                <div class="notice-overlay"></div>
                <div class="notice-card" style="background-image: url('${notice.image}');">
                    <div class="notice-card-title">${notice.title}</div>
                    <div class="notice-card-description">${notice.description}</div>
                </div>
            </div>
        `;
    });

    applyHoverEffects(); // 카드 효과 적용
}

// 카드 효과 적용
function applyHoverEffects() {
    document.querySelectorAll('.notice-container-wrapper').forEach((container) => {
        const overlay = container.querySelector('.notice-overlay');
        let mouseEnterPos = { x: 0, y: 0 };

        // 마우스 진입 시 위치 저장
        container.addEventListener('mouseenter', (e) => {
            const rect = container.getBoundingClientRect();
            mouseEnterPos = { x: e.clientX - rect.left, y: e.clientY - rect.top };
        });

        // 마우스 이동 효과
        container.addEventListener('mousemove', (e) => {
            const rect = container.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;

            // 오버레이 효과
            overlay.style.background = `radial-gradient(circle at ${x}px ${y}px, 
            rgba(255, 255, 255, 0.5) 10%, 
            rgba(255, 219, 112, 0.3) 30%, 
            transparent 70%)`;
            overlay.style.opacity = '1'; // 마우스 움직임 시 빛 활성화

            // 카드 회전 효과
            const rotateX = (y / rect.height - 0.5) * 45;
            const rotateY = (x / rect.width - 0.5) * -45;
            container.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
        });

        // 마우스 벗어날 때 효과
        container.addEventListener('mouseleave', (e) => {
            const rect = container.getBoundingClientRect();
            const mouseLeavePos = { x: e.clientX - rect.left, y: e.clientY - rect.top };

            // 진입-벗어남 방향 계산
            const deltaX = mouseLeavePos.x - mouseEnterPos.x;
            const deltaY = mouseLeavePos.y - mouseEnterPos.y;
            const rotateX = deltaY > 0 ? 15 : -15; // 상/하 방향 회전
            const rotateY = deltaX > 0 ? -15 : 15; // 좌/우 방향 회전

            // 부드러운 회전 후 초기화
            container.style.transform = `rotate3d(${rotateX / 15}, ${rotateY / 15}, 0, 20deg)`;
            overlay.style.filter = 'opacity(0)';
            setTimeout(() => {
                container.style.transform = 'rotateX(0) rotateY(0)';
            }, 1); // 초기화 시간
        });
    });
}

// 메인 페이지에서 카드 효과 활성화를 위해 DOMContentLoaded 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', () => {
    renderNotices(); // 리뷰 카드 렌더링
});

// 새 글 작성 창 열기 함수
function openNewPost() {
    const url = `/mung/support/notice/newPost`;  // 새 글 작성 페이지 URL
    window.open(url, '_blank', 'width=800,height=600,scrollbars=yes');  // 새 창으로 열기
}

