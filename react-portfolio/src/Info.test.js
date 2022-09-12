import { render, screen } from '@testing-library/react';
import Info from './Info';

test('renders expected text', () => {
  render(<Info />);
  const titleElement = screen.getByText(/portfolio project/i);
  const authorElement = screen.getByText(/by.*and/i);
  const toolsElement = screen.getByText(/created with.*react.js and.*spring/i);
  expect(titleElement).toBeInTheDocument();
  expect(authorElement).toBeInTheDocument();
  expect(toolsElement).toBeInTheDocument();
});
