import React from 'react'
import { Col, Container, Row } from "react-bootstrap";
import './Footer.css'

function Footer() {
  return (
    <Container>
        <Row>
          <Col className="footer">Copyright &copy; Pied-Piper</Col>
        </Row>
      </Container>
  )
}

export default Footer
