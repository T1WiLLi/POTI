import { Col, Container, ListGroup, Row } from "react-bootstrap";

function Home() {
    return (
        <Container fluid className="d-flex flex-column vh-100 justify-content-center align-items-center bg-dark text-white">
            <Row>
                <Col className="text-center">
                    <h1 className="display-1">Poti TGC</h1>
                    <p className="lead mt-3">Arrive bientôt...</p>
                </Col>
            </Row>
            <Row className="mt-5">
                <Col className="text-center">
                    <h2 className="mb-4">Contributeurs</h2>
                    <ListGroup variant="flush" className="text-white">
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>William</strong> - Créateur du projet
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Samuel C.</strong> - Backend + Frontend + Designer
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Nathan</strong> - Designer + Frontend
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Liza</strong> - Backend + Data master
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Zachary</strong> - Frontend
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Samuel A.</strong> - Frontend
                        </ListGroup.Item>
                        <ListGroup.Item className="bg-dark border-light text-white">
                            <strong>Philipe B.</strong> - QA
                        </ListGroup.Item>
                    </ListGroup>
                </Col>
            </Row>
        </Container>
    );
}

export default Home;