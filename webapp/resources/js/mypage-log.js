class LogCard extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode : "open" });
    }

    get name() {
        return this.getAttribute('name');
    }

    set name(val) {
        this.setAttribute('name', val);
    }
    
    get uno() {
        return this.getAttribute('uno');
    }

    set uno(val) {
        this.setAttribute('uno', val);
    }
    
    get date() {
    	return this.getAttribute('date');
    }
    
    set date(val) {
    	this.setAttribute('date', val);
    }

    static get observedAttributes() {
        return ["name", "date"];
    }

    attributeChangedCallback(prop, oldVal, newVal) {

    }

    connectedCallback() {
        this.render();
    }

    render() {
        this.shadowRoot.innerHTML = `
        <style>
            .log-card {
                display : flex;
                flex-direction : column;
                background-color : white;
                width : 280px;
                height : 120px;
                border-radius : 10px;
                padding : 20px;
                margin-top : 0;
                margin-bottom : 20px;
                transform : translateX(20%);
            }

            p {
                margin : 3px;
            }

            .name {
                font-size : 1.2rem;
            }

            .date {
                font-size : 0.9rem;
            }

            #review-button {
                width  : 270px;
                background-color : rgb(52, 152, 219);
                height : 45px;
                border-radius : 10px;
                border : none;
                outline : none;
                margin : 5px;
                position : relative;
                margin-top : 25px;
                margin-bottom : 0;
                color : white;
                font-weight : bold;
                font-size : 1rem;
                cursor : pointer;
            }

        </style>
        <div class="log-card">
        	<form action="writereview" method="get">
            <p class="name">${this.name}</p>
            <p class="date">${this.date}</p>
            <input type="hidden" name="tname" value="${this.name}">
            <input type="hidden" name="tdate" value="${this.date}">
            <input type="hidden" name="uno" value="${this.uno}">
            <input type="submit" id="review-button" value="리뷰 쓰러가기">
        </div>
        `;
    }
}

window.customElements.define('log-card', LogCard);